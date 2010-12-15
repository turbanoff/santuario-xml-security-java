/*
 * Copyright  1999-2004 The Apache Software Foundation.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.xml.security.keys.content.keyvalues;



import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.I18n;
import org.apache.xml.security.utils.JavaUtils;
import org.apache.xml.security.utils.SignatureElementProxy;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 *
 * @author $Author$
 */
public class RSAKeyValue extends SignatureElementProxy
        implements KeyValueContent {

   /** {@link org.apache.commons.logging} logging facility */
    static org.apache.commons.logging.Log log = 
        org.apache.commons.logging.LogFactory.getLog(
			RSAKeyValue.class.getName());

   /**
    * Constructor RSAKeyValue
    *
    * @param element
    * @param BaseURI
    * @throws XMLSecurityException
    */
   public RSAKeyValue(Element element, String BaseURI)
           throws XMLSecurityException {
      super(element, BaseURI);
   }

   /**
    * Constructor RSAKeyValue
    *
    * @param doc
    * @param modulus
    * @param exponent
    */
   public RSAKeyValue(Document doc, BigInteger modulus, BigInteger exponent) {

      super(doc);

      XMLUtils.addReturnToElement(this._constructionElement);
      this.addBigIntegerElement(modulus, Constants._TAG_MODULUS);
      this.addBigIntegerElement(exponent, Constants._TAG_EXPONENT);
   }

   /**
    * Constructor RSAKeyValue
    *
    * @param doc
    * @param key
    * @throws IllegalArgumentException
    */
   public RSAKeyValue(Document doc, Key key) throws IllegalArgumentException {

      super(doc);

      XMLUtils.addReturnToElement(this._constructionElement);

      if (JavaUtils.implementsInterface(
              key, "java.security.interfaces.RSAPublicKey")) {
         this.addBigIntegerElement(((RSAPublicKey) key).getModulus(),
                                   Constants._TAG_MODULUS);
         this.addBigIntegerElement(((RSAPublicKey) key).getPublicExponent(),
                                   Constants._TAG_EXPONENT);
      } else {
         Object exArgs[] = { Constants._TAG_RSAKEYVALUE,
                             key.getClass().getName() };

         throw new IllegalArgumentException(I18n
            .translate("KeyValue.IllegalArgument", exArgs));
      }
   }

   /**
    * Method getPublicKey
    *
    *
    * @throws XMLSecurityException
    */
   public PublicKey getPublicKey() throws XMLSecurityException {

      try {
         KeyFactory rsaFactory = KeyFactory.getInstance("RSA");

         // String JCE_RSA = org.apache.xml.security.algorithms.JCEMapper.translateURItoJCEID(Constants.ALGO_ID_SIGNATURE_RSA);
         // KeyFactory rsaFactory = KeyFactory.getInstance(JCE_RSA);
         RSAPublicKeySpec rsaKeyspec =
            new RSAPublicKeySpec(this
               .getBigIntegerFromChildElement(Constants._TAG_MODULUS, Constants
               .SignatureSpecNS), this
                  .getBigIntegerFromChildElement(Constants
                     ._TAG_EXPONENT, Constants.SignatureSpecNS));
         PublicKey pk = rsaFactory.generatePublic(rsaKeyspec);

         return pk;
      } catch (NoSuchAlgorithmException ex) {
         throw new XMLSecurityException("empty", ex);
      } catch (InvalidKeySpecException ex) {
         throw new XMLSecurityException("empty", ex);
      }
   }

   public String getBaseLocalName() {
      return Constants._TAG_RSAKEYVALUE;
   }
}
