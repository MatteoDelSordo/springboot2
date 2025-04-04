package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.KpiDTO;
import it.siinfo.springboot2.entity.SupplierProduct;
import it.siinfo.springboot2.repository.OrderRepository;
import it.siinfo.springboot2.repository.ProductRepository;
import it.siinfo.springboot2.repository.SupplierProductRepository;
import it.siinfo.springboot2.repository.SupplierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KPIService {

    private static final Logger log = LoggerFactory.getLogger (KPIService.class);
    final OrderRepository orderRepository;
    final SupplierRepository supplierRepository;
    final ProductRepository productRepository;
    final SupplierProductRepository supplierProductRepository;


    public KPIService(OrderRepository orderRepository,
                      SupplierRepository supplierRepository,
                      ProductRepository productRepository,
                      SupplierProductRepository supplierProductRepository) {
        this.orderRepository = orderRepository;
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
        this.supplierProductRepository = supplierProductRepository;
    }


    public KpiDTO pippo() {

        Long totalOrders = orderRepository.count ();
        Long totalSuppliers = supplierRepository.count ();
        Long totalProducts = productRepository.count ();
        List<SupplierProduct> supplierProductList = supplierProductRepository.findAll ();

        Double averagePrice = 0.00;

        try {

            for (SupplierProduct supplierProduct : supplierProductList) {
                averagePrice = averagePrice + supplierProduct.getPrice ();

            }
            averagePrice = averagePrice / supplierProductList.size ();
        } catch (ArithmeticException arithmeticException) {
            System.out.println ("Qualcosa nel calcolo della media del prezzo è andata storta");
        }

//        List<Double> list = supplierProductList.stream().map(SupplierProduct::getPrice).collect(Collectors.toList());
//        Double averagePrice = list.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        Double averageQuantityPerProduct = null;
        try {

            averageQuantityPerProduct = supplierProductList.stream ().map (SupplierProduct::getQuantity).mapToInt (value -> value).average ().orElse (
                    0);
        } catch (ArithmeticException arithmeticException) {
            System.out.println ("Qualcosa nel calcolo della media della quantità prodotto è andato storto ");
        }


        return new KpiDTO (totalOrders, totalSuppliers, totalProducts, averagePrice, averageQuantityPerProduct);
    }


}
