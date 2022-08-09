package com.example.ActiveMQ;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

public class ProductServiceTest {
//
//    @Mock
//    private ProductRepository productRepository;
//    @Mock
//    private ActiveMQProducer activeMQProducer;
//    @Mock
//    private ProductConverter productConverter;
//    @Mock
//    private Logger log;
//    @InjectMocks
//    private ProductService productService;
//
//    private final String SENT_PRODUCTS_MESSAGE = "Products number sent to migrate: ";
//    private final Long PRODUCT_ID_1 = 0L;
//    private final Long PRODUCT_ID_2 = 1L;
//    private final Long PRODUCT_ID_3 = 3L;
//    private final Long PRODUCT_ID_4 = 4L;
//    private ProductEntity non_migrated_entity_1;
//    private ProductEntity non_migrated_entity_2;
//    private ProductDto non_migrated_dto_1;
//    private ProductDto non_migrated_dto_2;
//    private ProductEntity migrated_entity_1;
//    private ProductEntity migrated_entity_2;
//    private ProductDto migrated_dto_1;
//    private ProductDto migrated_dto_2;
//    private List<ProductEntity> non_migrated_entities_list;
//    private Set<ProductDto> non_migrated_dto_set;
//    private List<ProductEntity> migrated_entities_list;
//    private Set<ProductDto> migrated_dto_set;
//
//
//    @BeforeEach
//    public void prepareToTest() {
//        MockitoAnnotations.openMocks(this);
//
//        non_migrated_dto_1 = new ProductDto(PRODUCT_ID_1);
//        non_migrated_dto_2 = new ProductDto(PRODUCT_ID_2);
//
//        non_migrated_entity_1 = ProductEntity.builder()
//                .id(PRODUCT_ID_1)
//                .migrated(Boolean.FALSE)
//                .name("name1")
//                .build();
//
//        non_migrated_entity_2 = ProductEntity.builder()
//                .id(PRODUCT_ID_2)
//                .migrated(Boolean.FALSE)
//                .name("name2")
//                .build();
//
//        non_migrated_entities_list = new ArrayList<>();
//        non_migrated_entities_list.add(non_migrated_entity_1);
//        non_migrated_entities_list.add(non_migrated_entity_2);
//
//        non_migrated_dto_set = new HashSet<>();
//        non_migrated_dto_set.add(non_migrated_dto_1);
//        non_migrated_dto_set.add(non_migrated_dto_2);
//
//        migrated_dto_1 = new ProductDto(PRODUCT_ID_3);
//        migrated_dto_2 = new ProductDto(PRODUCT_ID_4);
//
//        migrated_entity_1 = ProductEntity.builder()
//                .id(PRODUCT_ID_3)
//                .migrated(Boolean.FALSE)
//                .name("name3")
//                .build();
//
//        migrated_entity_2 = ProductEntity.builder()
//                .id(PRODUCT_ID_4)
//                .migrated(Boolean.FALSE)
//                .name("name4")
//                .build();
//
//        migrated_entities_list = new ArrayList<>();
//        migrated_entities_list.add(migrated_entity_1);
//        migrated_entities_list.add(migrated_entity_2);
//
//        migrated_dto_set = new HashSet<>();
//        migrated_dto_set.add(migrated_dto_1);
//        migrated_dto_set.add(migrated_dto_2);
//
//        when(productConverter.convertEntityToDto(non_migrated_entity_1)).thenReturn(non_migrated_dto_1);
//        when(productConverter.convertEntityToDto(non_migrated_entity_2)).thenReturn(non_migrated_dto_2);
//        when(productConverter.convertEntityToDto(migrated_entity_1)).thenReturn(migrated_dto_1);
//        when(productConverter.convertEntityToDto(migrated_entity_2)).thenReturn(migrated_dto_2);
//
//        when(productRepository.getReferenceById(PRODUCT_ID_1)).thenReturn(non_migrated_entity_1);
//        when(productRepository.getReferenceById(PRODUCT_ID_2)).thenReturn(non_migrated_entity_2);
//        when(productRepository.getReferenceById(PRODUCT_ID_3)).thenReturn(migrated_entity_1);
//        when(productRepository.getReferenceById(PRODUCT_ID_4)).thenReturn(migrated_entity_2);
//
//        doNothing().when(activeMQProducer).sendNonMigratedItems(any());
//        doNothing().when(log).info(any());
//    }
////
//    @Test
//    public void sendProductsToMigrate_existNonMigratedProducts_MessageWithRightCountOfProducts(){
//        String resultString;
//        String expectedString = SENT_PRODUCTS_MESSAGE + "2";
//        when(productRepository.findByMigrated(Boolean.FALSE)).thenReturn(non_migrated_entities_list);
//
//        resultString = productService.sendProductsToMigrate();
//
//        assertEquals(expectedString, resultString);
//        verify(productRepository).findByMigrated(Boolean.FALSE);
//        verify(productConverter).convertEntityToDto(non_migrated_entity_1);
//        verify(productConverter).convertEntityToDto(non_migrated_entity_2);
//        verify(activeMQProducer).sendNonMigratedItems(eq(non_migrated_dto_set));
//    }
//
//    @Test
//    public void sendProductsToMigrate_NotExistNonMigratedProducts_MessageWithZeroCountOfProducts(){
//        String resultString;
//        String expectedString = SENT_PRODUCTS_MESSAGE + "0";
//        when(productRepository.findByMigrated(Boolean.FALSE)).thenReturn(new ArrayList<>());
//
//        resultString = productService.sendProductsToMigrate();
//
//        assertEquals(expectedString, resultString);
//        verify(productRepository).findByMigrated(Boolean.FALSE);
//        verify(productConverter, never()).convertEntityToDto(any());
//        verify(activeMQProducer, never()).sendNonMigratedItems(any());
//    }
//
//    @Test
//    public void migrateIncomingProducts_SetNonMigratedProductsDtos_TurnMigratedInEntityToTrue(){
//        productService.migrateIncomingProducts(non_migrated_dto_set);
//
//        assertTrue(non_migrated_entity_1.getMigrated());
//        assertTrue(non_migrated_entity_2.getMigrated());
//        verify(productRepository).getReferenceById(PRODUCT_ID_1);
//        verify(productRepository).getReferenceById(PRODUCT_ID_2);
//    }
//
//    @Test
//    public void migrateIncomingProducts_SetMigratedProductsDtos_NothingChange(){
//        productService.migrateIncomingProducts(migrated_dto_set);
//
//        assertTrue(migrated_entity_1.getMigrated());
//        assertTrue(migrated_entity_2.getMigrated());
//        verify(productRepository).getReferenceById(PRODUCT_ID_3);
//        verify(productRepository).getReferenceById(PRODUCT_ID_4);
//    }

}
