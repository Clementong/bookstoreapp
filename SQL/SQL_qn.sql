-- Q1
SELECT count( DISTINCT customer_id) AS total_subscribers 
FROM tblSubscriptionInfo 
WHERE  EXTRACT(YEAR FROM CAST(subscription_end_date AS DATE)) = 2023 

-- Q2 - Assumptions: 
--      A subscription period of a month = 30 days. 
--      More than three months suggests > 3 months, not inclusive of 3 months.
SELECT count( DISTINCT customer_id) AS total_subscribers 
FROM tblSubscriptionInfo 
WHERE EXTRACT(YEAR FROM CAST(subscription_start_date AS DATE)) = 2022 
and extract( days FROM subscription_end_date - subscription_start_date) > 90;

-- Q3 - Assumptions: 
--      More than two products suggests > 2 products , not inclusive of 2 products.
With customer_product AS (
    SELECT customer_id, count(DISTINCT product_id) AS count_product FROM  tblSubscriptionInfo GROUP BY customer_id
) SELECT customer_id FROM customer_product WHERE count_product  > 2;

-- Q4 
SELECT product_id, count(DISTINCT customer_id) AS number_of_subscribers 
FROM tblSubscriptionInfo 
WHERE extract(year FROM CAST(subscription_start_date AS DATE)) = 2022 
GROUP BY product_id 
ORDER BY number_of_subscribers desc;

-- Q5
With cust_sub AS (
    SELECT a.customer_id, a.product_id, a.product_name, count(a.subscription_id) AS number_of_renewals  
    FROM tblSubscriptionInfo AS a join  tblSubscriptionInfo AS b 
    on a.customer_id = b.customer_id and a.product_id = b.product_id and a.subscription_end_date = b.subscription_start_date - INTERVAL '1 DAY' 
    GROUP BY a.customer_id, a.product_id, a.product_name 
) 
SELECT product_id, product_name, count(DISTINCT customer_id) AS number_of_subscribers 
FROM cust_sub WHERE number_of_renewals > 1 
GROUP BY product_id, product_name;

-- Q6 - Assumptions: 
--      Product name for all version will follow the same pattern "* YYYY" e.g. Autodesk 2021 to Autodesk 2022
--      Resubscription of a product in 2023 suggests that the subscription_start_date will be in the year 2023.
With subscription_details AS (
    SELECT *, LAG(left(product_name, length(product_name)-4)) OVER (PARTITION BY customer_id ORDER BY product_name,subscription_start_date) = left(product_name, length(product_name)-4) AS product
        , LAG(right(product_name,4)) OVER (PARTITION BY customer_id ORDER BY product_name, subscription_start_date) < right(product_name,4) AS upgrade 
    FROM tblSubscriptionInfo 
    ORDER BY customer_id, product_name, subscription_start_date
) 
SELECT DISTINCT(customer_name) 
FROM subscription_details  
WHERE upgrade = true and product = true and EXTRACT(YEAR FROM CAST(subscription_start_date AS DATE)) = 2023;
