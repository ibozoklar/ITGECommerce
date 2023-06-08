import React, { useEffect, useState } from "react";
import axios from "axios";

export default function HomeContent({ refreshProducts }) {
  const [products, setProducts] = useState([]);
  const userId = 3; // Fixed value for userId

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = () => {
    axios
      .get("http://localhost:9092/product/findAllProducts")
      .then((response) => {
        // Handle success
        setProducts(response.data);
      })
      .catch((error) => {
        // Handle error
        console.error(error);
      });
  };

  const addToBasket = (productId) => {
    const payload = {
      userId: userId,
      productId: productId,
      // Add other relevant data for the basket here
    };

    axios
      .put(`http://localhost:9092/basket/addItemToBasket/${userId}/${productId}`, payload)
      .then((response) => {
        // Handle success
        console.log("Item added to basket successfully.");
        refreshProducts(); // Call the refreshProducts function to update the products
      })
      .catch((error) => {
        // Handle error
        console.error("Failed to add item to basket:", error);
      });
  };

  return (
    <div className="home">
      {products.map((product) => (
        <div className="card" key={product.id}>
          {product.title} <br /> {product.brand} <br /> {product.price} TL
          <button onClick={() => addToBasket(product.id)}>Add to Basket</button>
        </div>
      ))}
    </div>
  );
}
