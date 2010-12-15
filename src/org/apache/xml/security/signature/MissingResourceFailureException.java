
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
package org.apache.xml.security.signature;





/**
 * Thrown by {@link org.apache.xml.security.signature.SignedInfo#verify} when
 * testing the signature fails because of uninitialized
 * {@link org.apache.xml.security.signature.Reference}s.
 *
 * @author Christian Geuer-Pollmann
 * @see ReferenceNotInitializedException
 */
public class MissingResourceFailureException extends XMLSignatureException {

   /** Field uninitializedReference */
   Reference uninitializedReference = null;

   /**
    * MissingKeyResourceFailureException constructor.
    * @param msgID
    * @param reference
    * @see #getReference
    */
   public MissingResourceFailureException(String _msgID, Reference reference) {

      super(_msgID);

      this.uninitializedReference = reference;
   }

   /**
    * Constructor MissingResourceFailureException
    *
    * @param _msgID
    * @param exArgs
    * @param reference
    * @see #getReference
    */
   public MissingResourceFailureException(String _msgID, Object exArgs[],
                                          Reference reference) {

      super(_msgID, exArgs);

      this.uninitializedReference = reference;
   }

   /**
    * Constructor MissingResourceFailureException
    *
    * @param _msgID
    * @param originalException
    * @param reference
    * @see #getReference
    */
   public MissingResourceFailureException(String _msgID,
                                          Exception _originalException,
                                          Reference reference) {

      super(_msgID, _originalException);

      this.uninitializedReference = reference;
   }

   /**
    * Constructor MissingResourceFailureException
    *
    * @param _msgID
    * @param exArgs
    * @param originalException
    * @param reference
    * @see #getReference
    */
   public MissingResourceFailureException(String _msgID, Object exArgs[],
                                          Exception _originalException,
                                          Reference reference) {

      super(_msgID, exArgs, _originalException);

      this.uninitializedReference = reference;
   }

   /**
    * used to set the uninitialized {@link org.apache.xml.security.signature.Reference}
    *
    * @param reference the Reference object
    * @see #getReference
    */
   public void setReference(Reference reference) {
      this.uninitializedReference = reference;
   }

   /**
    * used to get the uninitialized {@link org.apache.xml.security.signature.Reference}
    *
    * This allows to supply the correct {@link org.apache.xml.security.signature.XMLSignatureInput}
    * to the {@link org.apache.xml.security.signature.Reference} to try again verification.
    *
    * @return the Reference object
    * @see #setReference
    */
   public Reference getReference() {
      return this.uninitializedReference;
   }
}
