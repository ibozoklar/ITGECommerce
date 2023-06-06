import React, { useEffect, useState } from "react";
import axios from "axios";


export default function BasketPage() {

  const [products, setProducts] = useState([]);
  
  useEffect(() => {
    axios.get("http://localhost:9092/basket/listProducts/9")
      .then((response) => {
        // Handle success
        setProducts(response.data);
      })
      .catch((error) => {
        // Handle error
        console.error(error);
      });
  }, []);


    return (
      <div className="basketContent">

        <div className="top">
          {products.map((product) => (
          <div className="card" key={product.id}>
            {product.title} <br /> {product.brand} <br /> {product.price} TL
            <button onClick={() => addToBasket(product.id)}>Add to Basket</button>
          </div>
        ))}
        </div>

        <div className="bottom">
        </div>

      </div>
    );
};