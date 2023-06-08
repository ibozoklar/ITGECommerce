import React, { useEffect, useState } from "react";
import axios from "axios";

export default function BasketPage() {
  const [products, setProducts] = useState([]);
  const [basketId, setBasketId] = useState(null);

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = () => {
    axios
      .get("http://localhost:9092/basket/listProducts/9")
      .then((response) => {
        setProducts(response.data);
        setBasketId(response.data[0].basketId); // Assuming basketId is available in the response
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const removeFromBasket = (productId) => {
    const userid = 3; // Set the user ID as per your requirements
    axios
      .put(`http://localhost:9092/basket/removeItemFromBasket/${userid}/${productId}`)
      .then(() => {
        fetchProducts(); // Refetch the products after successful removal
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const purchaseProducts = () => {
    axios
      .put(`http://localhost:9092/basket/purchaseProducts/9`)
      .then(() => {
        console.log("Purchase successful!");
        fetchProducts();
        
      })
      .catch((error) => {
        console.error(error);
      });
  };

  return (
    <div className="basketContent">
      <div className="top">
        {products.map((product) => (
          <div className="card" key={product.id}>
            {product.title} <br /> {product.brand} <br /> {product.price} TL
            <button onClick={() => removeFromBasket(product.id)}>
              Remove from Basket
            </button>
          </div>
        ))}
      </div>

      <div className="bottom">
        <button onClick={purchaseProducts}>PURCHASE</button>
      </div>
    </div>
  );
}
