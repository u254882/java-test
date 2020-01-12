Feature: Want a basket to print out correctly


  Scenario: Basket without promotion out of date range
    Given The basket in file 'src/test/resources/fixtures/input/basket_out_of_date_range.txt' is processed.
    Then The console output matches the contents of the file 'src/test/resources/fixtures/output/basket_out_of_date_range_output.txt'.

  Scenario: Basket with apple only promotion
    Given The basket in file 'src/test/resources/fixtures/input/basket_apple_only_promotion.txt' is processed.
    Then The console output matches the contents of the file 'src/test/resources/fixtures/output/basket_apple_only_promotion_output.txt'.

  Scenario: Basket with soup and apple promotion
    Given The basket in file 'src/test/resources/fixtures/input/basket_soup_and_apple_promotion.txt' is processed.
    Then The console output matches the contents of the file 'src/test/resources/fixtures/output/basket_soup_and_apple_promotion_output.txt'.

  Scenario: Basket with soup promotion
    Given The basket in file 'src/test/resources/fixtures/input/basket_soup_promotion.txt' is processed.
    Then The console output matches the contents of the file 'src/test/resources/fixtures/output/basket_soup_promotion_output.txt'.

  Scenario: Basket without promotion not eligible products
    Given The basket in file 'src/test/resources/fixtures/input/basket_no_promotion.txt' is processed.
    Then The console output matches the contents of the file 'src/test/resources/fixtures/output/basket_no_promotion_output.txt'.
