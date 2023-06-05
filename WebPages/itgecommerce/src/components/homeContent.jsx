import React, { useEffect, useState } from "react";
import axios from "axios";

export default function HomeContent() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:9092/product/findAllProducts')
      .then(response => {
        // Handle success
        setProducts(response.data);
      })
      .catch(error => {
        // Handle error
        console.error(error);
      });
  }, []);

  return (
    <div>
      <h2>Products</h2>
      <ul>
        {products.map(product => (
          <li key={product.id}>{product.title} <br /> {product.brand}  <br /> {product.price} TL<br /><br /></li>
        ))}
      </ul>
    </div>
  );
}
