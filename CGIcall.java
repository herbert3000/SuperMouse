import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

class CGIcall {
   String strUrl;

   CGIcall() {
      this.strUrl = "";
   }

   CGIcall(String var1) {
      this.strUrl = var1;
   }

   String call(String var1) {
      String var2 = "";

      try {
         URL var3 = new URL(this.strUrl);
         URLConnection var4 = var3.openConnection();
         var4.setDoInput(true);
         var4.setDoOutput(true);
         var4.setUseCaches(false);
         DataOutputStream var5 = new DataOutputStream(var4.getOutputStream());
         var5.writeChars(var1);
         var5.close();
         DataInputStream var6 = new DataInputStream(var4.getInputStream());
         var2 = var6.readLine();
         var6.close();
      } catch (MalformedURLException var7) {
         System.err.println("MalformedURLException" + var7);
      } catch (IOException var8) {
         System.err.println("IOException" + var8);
      }

      return var2;
   }

   void setURL(String var1) {
      this.strUrl = var1;
   }
}
