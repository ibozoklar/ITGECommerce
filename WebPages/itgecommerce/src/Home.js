// Home.js
import React, { useContext, useEffect, useState } from 'react';
import { AuthContext } from './AuthContext';
import Header from './Header.js';
import axios from "axios";
import './Home.css'; // Import the CSS file for Home component styling

const Home = () => {
  const { userid } = useContext(AuthContext);
  console.log(userid);
  const [products, setProducts] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:9092/product/findAllProducts")
      .then((response) => {
        // Handle success
        setProducts(response.data);
      })
      .catch((error) => {
        // Handle error
        console.error(error);
      });
  }, []);

  const addToBasket = (productId) => {
    const payload = {
      userId: userid,
      productId: productId,
      // Add other relevant data for the basket here
    };

    axios.put(`http://localhost:9092/basket/addItemToBasket/${userid}/${productId}`, payload)
      .then((response) => {
        // Handle success
        console.log("Item added to basket successfully.");
      })
      .catch((error) => {
        // Handle error
        console.error("Failed to add item to basket:", error);
      });
  };

  return (
    <div>
      <Header />
      <div className="home">
        {products.map((product) => (
          <div className="card" key={product.id}>
            <div className="card-content">
              <h3 className="card-title">{product.title}</h3>
              <p className="card-brand">{product.brand}</p>
              <p className="card-price">{product.price} TL</p>
              <button className="add-to-basket-btn" onClick={() => addToBasket(product.id)}>Add to Basket</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Home;
