import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from 'react-router-dom';
import { I18nextProvider, initReactI18next } from 'react-i18next';
import i18n from 'i18next';

import enTranslation from './locales/en.json';
import trTranslation from './locales/tr.json';

// Configure i18next
i18n.use(initReactI18next).init({
  resources: {
    en: {
      translation: enTranslation,
    },
    tr: {
      translation: trTranslation,
    },
  },
  lng: 'en', // Set the initial language
  fallbackLng: 'en', // Fallback language if translation is missing for the current language
  interpolation: {
    escapeValue: false,
  },
});

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      <I18nextProvider i18n={i18n}>
        <App />
      </I18nextProvider>
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
);

reportWebVitals();
