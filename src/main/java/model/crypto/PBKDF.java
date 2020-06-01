package model.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Hex;

public class PBKDF implements KDF {

    private static final int KEYLENGTH = 256;
    private static final int ROUNDS = 10000;

    @Override
    public final byte[] generateKey(final byte[] password, final byte[] salt, final int rounds) {
        final PBEKeySpec spec = new PBEKeySpec(new String(password).toCharArray(), salt, rounds, KEYLENGTH);
        SecretKeyFactory skf = null;
        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] key = null;
        try {
            key = skf.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        System.out.println(Hex.encodeHex(key));
        return key;
    }

    @Override
    public final int getDefaultRounds() {
        return PBKDF.ROUNDS;
    }

    @Override
    public final boolean isTweakable() {
        return false;
    }

    @Override
    public final void setMemory(final int memory) {
        return;
    }

    @Override
    public final void setParallelism(final int parallelism) {
        return;
    }

    @Override
    public void setKeySize(int keySize) {
        // TODO Auto-generated method stub
        
    }

}
