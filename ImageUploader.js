import React, { useState } from 'react';
import  imageCompression  from 'browser-image-compression';
import pako from 'pako';

const ImageUploader = () => {
  const [base64Image, setBase64Image] = useState('');
  const [compressedData, setCompressedData] = useState('');
//   Compresser le texte kaalalao en petite zavatra
  const handleCompress = () => {
    const compressedData = pako.gzip(base64Image, { to: 'string' });
    console.log('Texte compressé :', compressedData);
    setCompressedData(compressedData);
  };
//decompresser ilaina amle refa azo le donne avy any anaty base 
const handleDecompressAndDisplayImage = () => {
    try {
      const decompressedData = pako.ungzip(compressedData, { to: 'string' });
      console.log('Texte décompressé :', decompressedData);

      // Utiliser decompressedData comme source (src) de l'image
      setCompressedData(decompressedData);
    } catch (error) {
      console.error('Erreur lors de la décompression :', error);
    }
  };
  const handleImageChange = async (e) => {
    const file = e.target.files[0];

    if (file) {
      const options = {
        maxSizeMB: 1, // Max size in megabytes
        maxWidthOrHeight: 800, // Max width or height
      };

      try {
        const compressedFile = await imageCompression(file, options);
        const base64String = await convertToBase64(compressedFile);
        setBase64Image(base64String);
        handleCompress();
      } catch (error) {
        console.error('Error compressing image:', error);
      }
      
    }
  };
  
  console.log(base64Image);
  console.log(compressedData);

  const convertToBase64 = (file) => {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.onload = () => resolve(reader.result.split(',')[1]);
      reader.onerror = (error) => reject(error);
      reader.readAsDataURL(file);
    });
  };

  return (
    <div>
      <input type="file" onChange={handleImageChange} />
      {base64Image && (
        <div>
          <img src={`data:image/jpeg;base64,${base64Image}`} alt="Compressed" />
          <p>Compressed Image (Base64)</p>
        </div>
      )}
    </div>
  );
};

export default ImageUploader;
