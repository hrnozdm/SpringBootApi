package com.dailycodework.dream_shops.service.product;
import com.dailycodework.dream_shops.Model.Category;
import com.dailycodework.dream_shops.Model.Image;
import com.dailycodework.dream_shops.Model.Product;
import com.dailycodework.dream_shops.dto.ImageDto;
import com.dailycodework.dream_shops.dto.ProductDto;
import com.dailycodework.dream_shops.exception.ResourceNotFoundException;
import com.dailycodework.dream_shops.repository.CategoryRepository;
import com.dailycodework.dream_shops.repository.ImageRepository;
import com.dailycodework.dream_shops.repository.ProductRepository;
import com.dailycodework.dream_shops.request.AddProductRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final ModelMapper modelMapper;

    @Override
    public Product addProduct(AddProductRequest request) {
        Category category = categoryRepository.findByName(request.getCategory().getName());
        if (category == null) {
            category = new Category(request.getCategory().getName());
            categoryRepository.save(category);
        }

        Product product = createProduct(request, category);
        return productRepository.save(product);

    }

    private Product createProduct(AddProductRequest request, Category category){
     return new Product(
             request.getName(),
             request.getBrand(),
             request.getPrice(),
             request.getInventory(),
             request.getDescription(),
             category
     );
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
    }

    @Override
    public void deleteProductById(Long id) {

        productRepository.findById(id).
                ifPresentOrElse(productRepository::delete,()->{throw new ResourceNotFoundException("Product Not Found");});

       /* Product product=productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
        productRepository.delete(product);*/

    }

    @Override
    public void updateProduct(Long productId, Product updatedProduct) {

         Product existingProduct=productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product Not Found"));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setBrand(updatedProduct.getBrand());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setInventory(updatedProduct.getInventory());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setCategory(updatedProduct.getCategory());

        productRepository.save(existingProduct);

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category,brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand,name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand,name);
    }

    @Override
    public List<ProductDto> getConvertedProducts(List<Product> products) {
        return products.stream().map(this::convertToDto).toList();
    }
    @Override
    public ProductDto convertToDto(Product product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageDto> imageDtos = images.stream()
                .map(image -> modelMapper.map(image, ImageDto.class))
                .toList();
        productDto.setImages(imageDtos);
        return productDto;
    }
}
