package restaurantVote;

import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import ru.CryptoPro.JCP.tools.Decoder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class RestaurantVoteApplication {


	public static void main(String[] args) {
		SpringApplication.run(RestaurantVoteApplication.class, args);

		try {
			InputStream cadesCms = new FileInputStream("C:\\Synesis\\CV_Ryvkin_der.pdf.sig");
			InputStream inputStream = new FileInputStream("C:\\Synesis\\CV_Ryvkin.pdf");
			byte[] data = (new Decoder()).decodeBuffer(inputStream);
			var signatureBytes = cadesCms.readAllBytes();
			CMSSignedData cmsSignedData = new CMSSignedData(signatureBytes);
			var certStoreInSig = cmsSignedData.getSignedContent();
			var certInfo = cmsSignedData.getSignerInfos();
			//CAdESSignature cAdESSignature = new CAdESSignature(cadesCmsBytes, null, null);
			var sgnrs = cmsSignedData.getSignerInfos().getSigners();
			var certific = cmsSignedData.getCertificates();
			var attr = cmsSignedData.getAttributeCertificates();
			int temp = 0;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CMSException e) {
			e.printStackTrace();
		}
		//byte[] data1 = Base64.getDecoder().decode(cadesCms.readAllBytes());
		//InputStream signedData = new FileInputStream("C:\\Synesis\\CV_Ryvkin_copy.pdf");
		//BASE64Decoder decoder = new BASE64Decoder();
		//byte[] data1 = cadesCms.readAllBytes();

		//System.setProperty("com.sun.security.enableCRLDP", "true"); // проверка цепочки с использованием CRLDP сертификата
		//byte[] SIGN = (new Decoder()).decodeBuffer(new ByteArrayInputStream(cadesCms.readAllBytes()));
		// Читаем подпись из файла. (new Decoder()).decodeBuffer(cadesCms);

		//Array.readFile("C:\\Synesis\\CV_Ryvkin_copy.pdf.sig");

		int t = 0;
//		CAdESSigner cAdESSigner = cAdESSignature.getCAdESSignerInfo(0);
//		AttributeTable attributeTable = cAdESSigner.getSignerSignedAttributes();
//				//SignerInfo signerInfo = new SignerInfo();
	}

//	Cipher cipher;
//	// Пример генераци  простого ключа
////	byte[] keyBytes   = new byte[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
////	String algorithm  = "RawBytes";
////	SecretKeySpec key = new SecretKeySpec(keyBytes, algorithm);
//
//	KeyGenerator keyGenerator;
//	SecureRandom secureRandom = new SecureRandom();
//
//	{
//		try {
//			// Пример генерации сложного ключа
//			keyGenerator = KeyGenerator.getInstance("AES");
//			int keyBitSize = 256;
//			keyGenerator.init(keyBitSize, secureRandom);
//			SecretKey secretKey = keyGenerator.generateKey();
//
//			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//			cipher.init(Cipher.DECRYPT_MODE, secretKey);
//
//			byte[] plainText  = "abcdefghijklmnopqrstuvwxyz".getBytes("UTF-8");
//			// Расшифровываем данные
//			byte[] cipherText = cipher.doFinal(plainText);
//
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (NoSuchPaddingException e) {
//			e.printStackTrace();
//		} catch (InvalidKeyException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (IllegalBlockSizeException e) {
//			e.printStackTrace();
//		} catch (BadPaddingException e) {
//			e.printStackTrace();
//		}
//	}
//
//
//	MessageDigest messageDigest;
//
//	{
//		try {
//			// создается экземпляр MessageDigest, который использует
//			// внутренний алгоритм криптографического хэширования SHA-256 для вычисления дайджестов сообщений.
//			messageDigest = MessageDigest.getInstance("SHA-256");
//
//
//			// Пример вычисления дайджеста сообщения
//			// Само сообщение
//			byte[] data1 = "0123456789".getBytes("UTF-8");
//			byte[] data2 = "abcdefghijklmnopqrstuvxyz".getBytes("UTF-8");
//
//			// Вычисление его дайджеста
//			messageDigest.update(data1);
//			messageDigest.update(data2);
//
//			// Получаем дайджест сообщения
//			byte[] digest = messageDigest.digest();
//
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//	}
//
//
//	Signature signature;
//
//	{
//		try {
//			signature = Signature.getInstance("SHA256WithDSA");
//
//			// Создаем асмимметричную пару - открытый/закрытый цключ
//			secureRandom = new SecureRandom();
//			// При создании передаем название алгоритма шифрования
//			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
//			KeyPair keyPair = keyPairGenerator.generateKeyPair();
//
//			// Пример инициализации экземпляра подписи в режиме подписи:
//			signature.initSign(keyPair.getPrivate(), secureRandom);
//
//			// После инициализации экземпляра подписи, его можно использовать для
//			// подписи данных. Это делается вызовом метода update(),
//			// передавая данные для подписи в качестве параметра.
//			byte[] data = "abcdefghijklmnopqrstuvxyz".getBytes("UTF-8");
//			signature.update(data);
//
//			// Подписываем
//			byte[] digitalSignature = signature.sign();
//
//
//			// Чтобы проверить подпись, нужно инициализировать экземпляр подписи в режиме проверки путем вызова
//			// метода initVerify(...), передавая в качестве параметра открытый ключ, который используется для
//			// проверки подписи.
//			signature.initVerify(keyPair.getPublic());
//
//
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (InvalidKeyException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (SignatureException e) {
//			e.printStackTrace();
//		}
//	}
// System.setProperty("file.encoding", "UTF-8");
// Security.addProvider(new JCSP()); // провайдер JCSP

// CAdESSigner cAdESSigner = new
}

// Security.addProvider(new RevCheck());// провайдер проверки сертификатов JCPRevCheck
// (revocation-провайдер)
// Security.addProvider(new CryptoProvider());// провайдер шифрования JCryptoP
