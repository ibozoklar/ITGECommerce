import React, { useContext, useEffect, useState } from 'react';
import axios from 'axios';
import { AuthContext } from './AuthContext';
import { useTranslation } from 'react-i18next';

export default function BasketPage() {
  const [products, setProducts] = useState([]);
  const [basketId, setBasketId] = useState(null);
  const { userid } = useContext(AuthContext);
  const { t } = useTranslation();

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = () => {
    axios
      .get(`http://localhost:9092/basket/listProductsFromUserBasket/${userid}`)
      .then((response) => {
        setProducts(response.data);
        setBasketId(response.data[0].basketId); // Assuming basketId is available in the response
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const removeFromBasket = (productId) => {
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
      .put(`http://localhost:9092/basket/purchaseProductsInUserBasket/${userid}`)
      .then(() => {
        console.log('Purchase successful!');
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
            <strong>{product.title}</strong> <br /> {product.brand} <br /><br /> <strong>{product.price} TL</strong>
            <button onClick={() => removeFromBasket(product.id)}>
              {t('basket.remove')}
            </button>
          </div>
        ))}
      </div>

      <div className="bottom">
        <button onClick={purchaseProducts}>{t('basket.purchase')}</button>
      </div>
    </div>
  );
}
