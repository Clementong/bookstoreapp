**Q: When a customer updates their contact phone number, what query should we run in order to save that update to the database?**

Answer : 

*Assuming that each field is updated seperately*

> ` UPDATE tblSubscriptionInfo
SET customer_contact_phone = "912345678"
WHERE customer_id = "12345678" `


**Q: We've noted that the phone number update feature in the web application is too slow, and have identified that the update query is the primary bottleneck. What could we do to speed up this query?** 

Answer:

When examining the table schemas, it becomes evident that several rows within the dataset may pertain to the same customer. Consequently, modifying the phone number for a particular customer could lead to multiple line-level alterations (one to many relationship between customer and subscriptions) . To mitigate this issue and streamline data management, the table can be further norminalized.

This entails dividing the table into two distinct entities: 
- Firstly, a "customer" table, which comprises exclusively unique customer records and using cutomer_id as the primary key.
- Secondly, the remaining rows would be relegated to a separate table (“subscription table”), with only customer_id acting as a foreign key to cutomer_id in the previously mentioned “customer” table.

In doing so, having to change customer information would be much faster as the update operation would be searching a lesser number of rows. Additionally, it can result in better memory management as retrieving product and subscription information would not result in retrieving customer information as well. When customer information is needed, a `join` between the subscription and customer table can be used to retrieve the relevant information. 

Additionally to improve the overall performance of the database, an index or composite index can be used. This increases the search speeds as indexes are used for efficient data structures ,meant for search and sorting. 

An example of how to add an index to this table is as seen below : 

1) “Customer” Table, to improve search speeds given name and id. 
` CREATE INDEX customer_id_contact_index ON tblSubscriptionInfo (customer_id,customer_name)`
    *Note: customer_contact_phone is not indexed because if this field requires frequent changes, writing to this field might be slowed down as the data structure of the index needs to be changed.* 
    <br />
2) “Subscription" Table, to improve search speeds for customer information given subscription_id
    `CREATE INDEX subscription_customer_id_index ON tblSubscriptionInfo (subscription_id ,customer_id)`

