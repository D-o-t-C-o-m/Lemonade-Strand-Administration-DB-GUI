
##lemon stand administration##

The MainTest goal of the application is to sell lemonade. There are many types of lemonades made using a variety of recipes that combine a number of products. We need to keep track of our stocks of products and of our suppliers. A product has a name, a quantity stock, a price, and is provided by a single supplier. A supplier has a name and an email address used as a point of contact when our stock of products is almost empty. Our application should manage the deposit by adding, removing, modifying, and listing the products and suppliers.

Our application should be able to read a file containing a list of lemonades recipes, each made from a certain number and combination of products. Each type of lemonade will have a different name, a selling price and a list of required products.

Now that we can handle our stocks and recipes, we need to start selling. When a customer comes, we should be able to create an order to sell the lemonades. For each order, we need to ask the customer what lemonade he wants to buy and the quantity, and we need to calculate the total price. When a lemonade is sold, the product stock should be updated according to the recipe.

At the end of the day, we need to do some reporting. First, we should be able to generate a daily sales report and secondly, we need to identify which products have low stock levels.

Every operation performed in our application must be saved even if we shut down our computers. All our data should be stored in text files and any data entered by a user must be validated.


    - As a lemonade stand administrator I want to add, remove, update, display all suppliers so that I can manage my deposit.
    - As a lemonade stand administrator I want to add, remove, update, display all products so that I can manage my deposit and create lemonade types.
    - As a lemonade stand administrator I want to create a lemonade recipe so that I can sell the lemonade.
    - As a lemonade stand administrator I want to create an order so that I can keep track of my sales.
    - As a lemon stand administrator, I want to be able create a sales report for each day so that I can deliver my products.
    - As a lemon stand administrator, I want to be able create a stock report so that I can contact my supplier for new products.