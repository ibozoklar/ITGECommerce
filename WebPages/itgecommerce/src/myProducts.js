import React, { useContext, useEffect, useState } from "react";
import axios from "axios";
import { AuthContext } from "./AuthContext";
import { useTranslation } from 'react-i18next';

export default function MyProducts(){

    const [products, setProducts] = useState([]);
    const { userid } = useContext(AuthContext);
    const { t } = useTranslation();

    useEffect(() => {
        fetchProducts();
      }, []);
    
      const fetchProducts = () => {
        axios
          .get(`http://localhost:9092/user/findAllPurchasedProducts/${userid}`)
          .then((response) => {
            setProducts(response.data);
          })
          .catch((error) => {
            console.error(error);
          });
      };


    return(

      <div className="myProductsContent">
        <h3>{t('usermenu.myproducts')}</h3>
      
        {products.map((product) => (
          <div className="card" key={product.id}>
            <strong>{product.title}</strong><br /> {product.brand} <br /> <br /> <strong>{product.price} TL</strong>
          </div>
        ))}
      
    </div>
    );
}