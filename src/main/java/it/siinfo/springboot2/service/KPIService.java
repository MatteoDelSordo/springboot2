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

@Service
public class KPIService {

    private static final Logger log = LoggerFactory.getLogger (KPIService.class);
    final OrderRepository orderRepository;
    final SupplierRepository supplierRepository;
    final ProductRepository productRepository;
    final SupplierProductRepository supplierProductRepository;


    public KPIService (OrderRepository orderRepository,
                       SupplierRepository supplierRepository,
                       ProductRepository productRepository,
                       SupplierProductRepository supplierProductRepository) {
        this.orderRepository = orderRepository;
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
        this.supplierProductRepository = supplierProductRepository;
    }


    public KpiDTO pippo () {

        log.info ("Entro nel medoto per calcolare il totale degli ordini, dei fornitori, dei prodotti e la media del "
                + "prezzo e della quantità per prodotto");
        log.debug ("Inizio le query");


        log.debug ("Query per contare gli ordini totali");
        Long totalOrders = orderRepository.count ();
        log.debug ("risultato = {}", totalOrders);
        log.debug ("Query per contare i fornitori totali");
        Long totalSuppliers = supplierRepository.count ();
        log.debug ("risultato = {}", totalSuppliers);
        log.debug ("Query per contare i prodotti totali");
        Long totalProducts = productRepository.count ();
        log.debug ("risultato = {}", totalProducts);
        log.debug ("Query per recuperare i prezzi e le quantita dei prodotti");
        List<SupplierProduct> supplierProductList = supplierProductRepository.findAll ();
        log.debug ("Risultato = {}", supplierProductList);

        Double averagePrice = 0.00;

        try {
            log.debug ("Inizio del ciclo for per recuperare il totale dei prezzi");

            for (SupplierProduct supplierProduct : supplierProductList) {
                averagePrice = averagePrice + supplierProduct.getPrice ();
                log.debug ("dentro il ciclo for");
            }
            averagePrice = averagePrice / supplierProductList.size ();
            log.debug ("prezzo medio: {}", averagePrice);
        } catch (ArithmeticException arithmeticException) {
            System.out.println ("Qualcosa nel calcolo della media del prezzo è andata storta");
        }

//        List<Double> list = supplierProductList.stream().map(SupplierProduct::getPrice).collect(Collectors.toList());
//        Double averagePrice = list.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        Double averageQuantityPerProduct = null;
        try {
            log.debug ("Recuperaro del totale della quantità e calcolo media ");
            averageQuantityPerProduct =
                    supplierProductList.stream ().map (SupplierProduct::getQuantity).mapToInt (value -> value).average ().orElse (
                    0);
            log.debug ("Media quantità: {}", averageQuantityPerProduct);
        } catch (ArithmeticException arithmeticException) {
            System.out.println ("Qualcosa nel calcolo della media della quantità prodotto è andato storto");
        }


        log.info ("fine metodo");

        return new KpiDTO (totalOrders, totalSuppliers, totalProducts, averagePrice, averageQuantityPerProduct);
    }


}
