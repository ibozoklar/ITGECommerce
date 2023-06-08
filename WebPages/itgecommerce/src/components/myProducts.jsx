import React, { useEffect, useState } from "react";
import axios from "axios";


export default function MyProducts(){

    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetchProducts();
      }, []);
    
      const fetchProducts = () => {
        axios
          .get("http://localhost:9092/user/findAllPurchasedProducts/3")
          .then((response) => {
            setProducts(response.data);
          })
          .catch((error) => {
            console.error(error);
          });
      };


    return(

      <div className="myProductsContent">
      
        {products.map((product) => (
          <div className="card" key={product.id}>
            {product.title} <br /> {product.brand} <br /> {product.price} TL
          </div>
        ))}
      
    </div>
    );
}