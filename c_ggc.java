import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

class c_ggc {
   Image os_img;
   Graphics os_g;
   int os_width;
   int os_height;
   Image pt1b_img;
   Graphics pt1b_g;
   Image[] ptf_img = new Image[4];
   Color bc;
   int[] kdot_x = new int[100];
   int[] kdot_y = new int[100];
   int[] map_data = new int[25600];
   Image[] bg_img = new Image[100];
   Image[] bg_img_b = new Image[100];
   Graphics[] bg_g = new Graphics[100];
   boolean keyf_start = false;
   boolean keyf_left = false;
   boolean keyf_right = false;
   boolean keyf_up = false;
   boolean keyf_down = false;
   boolean keyf_tr1 = false;
   boolean keyf_tr2 = false;
   boolean keyf_tr3 = false;
   boolean keyf_tr4 = false;
   int keyf_dkc;

   public void back_fill() {
      this.os_g.setColor(this.bc);
      this.os_g.fillRect(0, 0, this.os_width, this.os_height);
   }

   public void keyf_up(int var1) {
      switch(var1) {
      case 32:
         this.keyf_tr1 = false;
         return;
      case 50:
         this.keyf_down = false;
         return;
      case 52:
         this.keyf_left = false;
         return;
      case 54:
         this.keyf_right = false;
         return;
      case 56:
         this.keyf_up = false;
         return;
      case 67:
         this.keyf_tr2 = false;
         return;
      case 83:
         this.keyf_start = false;
         return;
      case 86:
         this.keyf_tr2 = false;
         return;
      case 88:
         this.keyf_tr2 = false;
         return;
      case 90:
         this.keyf_tr1 = false;
         return;
      case 99:
         this.keyf_tr2 = false;
         return;
      case 115:
         this.keyf_start = false;
         return;
      case 118:
         this.keyf_tr2 = false;
         return;
      case 120:
         this.keyf_tr2 = false;
         return;
      case 122:
         this.keyf_tr1 = false;
         return;
      case 1004:
         this.keyf_up = false;
         return;
      case 1005:
         this.keyf_down = false;
         return;
      case 1006:
         this.keyf_left = false;
         return;
      case 1007:
         this.keyf_right = false;
         return;
      default:
      }
   }

   public void map_data_set(int[] var1) {
      int var2 = 0;

      do {
         int var3 = 0;

         do {
            int var4 = var2 * 160 + var3;
            this.map_data[var4] = var1[var4];
            ++var3;
         } while(var3 <= 159);

         ++var2;
      } while(var2 <= 159);

   }

   c_ggc(Image var1, Image var2, Image var3, Image var4, Image var5, Image var6, Image[] var7) {
      this.os_img = var1;
      this.os_g = this.os_img.getGraphics();
      this.os_width = var1.getWidth((ImageObserver)null);
      this.os_height = var1.getHeight((ImageObserver)null);
      this.pt1b_img = var2;
      this.pt1b_g = this.pt1b_img.getGraphics();
      this.ptf_img[0] = var3;
      this.ptf_img[1] = var4;
      this.ptf_img[2] = var5;
      this.ptf_img[3] = var6;
      this.bc = new Color(1, 1, 1);
      int var8 = 0;

      do {
         int var9 = var8 % 10;
         int var10 = (var8 - var9) / 10;
         this.kdot_x[var8] = -var9 * 32;
         this.kdot_y[var8] = -var10 * 32;
         ++var8;
      } while(var8 <= 99);

      var8 = 0;

      do {
         this.bg_img[var8] = var7[var8];
         this.bg_img_b[var8] = this.bg_img[var8];
         this.bg_g[var8] = this.bg_img[var8].getGraphics();
         ++var8;
      } while(var8 <= 99);

      this.map_bg_set();
      this.map_clear();
      this.keyf_start = false;
      this.keyf_left = false;
      this.keyf_right = false;
      this.keyf_up = false;
      this.keyf_down = false;
      this.keyf_tr1 = false;
      this.keyf_tr2 = false;
      this.keyf_tr3 = false;
      this.keyf_tr4 = false;
      this.keyf_dkc = 0;
   }

   public void map_bg1_palet(int var1, int var2) {
      this.bg_img[var1] = this.bg_img[var2];
      this.bg_g[var1] = this.bg_img[var1].getGraphics();
   }

   public void pt_draw(int var1, int var2, int var3, int var4) {
      this.pt1b_g.drawImage(this.os_img, -var2, -var3, (ImageObserver)null);
      this.pt1b_g.drawImage(this.ptf_img[var4], this.kdot_x[var1], this.kdot_y[var1], (ImageObserver)null);
      this.os_g.drawImage(this.pt1b_img, var2, var3, (ImageObserver)null);
   }

   public void map_clear() {
      int var1 = 0;

      do {
         int var2 = 0;

         do {
            this.map_data[var1 * 160 + var2] = 0;
            ++var2;
         } while(var2 <= 159);

         ++var1;
      } while(var1 <= 159);

   }

   public void keyf_down(int var1) {
      this.keyf_dkc = var1;
      switch(var1) {
      case 32:
         this.keyf_tr1 = true;
         return;
      case 50:
         this.keyf_down = true;
         return;
      case 52:
         this.keyf_left = true;
         return;
      case 54:
         this.keyf_right = true;
         return;
      case 56:
         this.keyf_up = true;
         return;
      case 67:
         this.keyf_tr2 = true;
         return;
      case 83:
         this.keyf_start = true;
         return;
      case 86:
         this.keyf_tr2 = true;
         return;
      case 88:
         this.keyf_tr2 = true;
         return;
      case 90:
         this.keyf_tr1 = true;
         return;
      case 99:
         this.keyf_tr2 = true;
         return;
      case 115:
         this.keyf_start = true;
         return;
      case 118:
         this.keyf_tr2 = true;
         return;
      case 120:
         this.keyf_tr2 = true;
         return;
      case 122:
         this.keyf_tr1 = true;
         return;
      case 1004:
         this.keyf_up = true;
         return;
      case 1005:
         this.keyf_down = true;
         return;
      case 1006:
         this.keyf_left = true;
         return;
      case 1007:
         this.keyf_right = true;
         return;
      default:
      }
   }

   public void map_bg_set() {
      int var1 = 0;

      do {
         this.bg_img[var1] = this.bg_img_b[var1];
         this.bg_g[var1] = this.bg_img[var1].getGraphics();
         ++var1;
      } while(var1 <= 99);

      var1 = 0;

      do {
         int var2 = 0;

         do {
            int var3 = var1 * 10 + var2;
            this.bg_g[var3].setColor(this.bc);
            this.bg_g[var3].fillRect(0, 0, 32, 32);
            this.bg_g[var3].drawImage(this.ptf_img[0], -var2 * 32, -var1 * 32, (ImageObserver)null);
            ++var2;
         } while(var2 <= 9);

         ++var1;
      } while(var1 <= 9);

   }

   public int map_bg1_get(int var1, int var2) {
      if (var1 >= 0 && var1 <= 5119 && var2 >= 0 && var2 <= 5119) {
         int var3 = var1 / 32;
         int var4 = var2 / 32;
         return this.map_data[var4 * 160 + var3];
      } else {
         return -1;
      }
   }

   public boolean map_jimen(int var1, int var2) {
      int var3 = var1 / 32;
      int var4 = (var1 + 31) / 32;
      int var5 = (var2 + 32) / 32;
      if (var5 >= 0 && var5 <= 159) {
         var5 *= 160;
         if (var3 >= 0 && var3 <= 159 && this.map_data[var5 + var3] >= 90) {
            return true;
         } else {
            return var4 >= 0 && var4 <= 159 && this.map_data[var5 + var4] >= 90;
         }
      } else {
         return false;
      }
   }

   public void map_bg1_put(int var1, int var2, int var3) {
      this.map_data[var3 * 160 + var2] = var1;
   }

   public void back_color(int var1, int var2, int var3) {
      this.bc = new Color(var1, var2, var3);
   }

   public void map_draw(int var1, int var2) {
      int var5 = var1 / 32;
      int var6 = var2 / 32;
      int var7 = var1 - var5 * 32;
      int var8 = var6 * 32 - var2;
      int var9 = var6 * 160 + var5;
      int var10 = 0;

      do {
         int var11 = this.map_data[var9++];
         if (var11 != 0) {
            this.os_g.drawImage(this.bg_img[var11], -var7, var8, (ImageObserver)null);
         }

         var11 = this.map_data[var9++];
         if (var11 != 0) {
            this.os_g.drawImage(this.bg_img[var11], 32 - var7, var8, (ImageObserver)null);
         }

         var11 = this.map_data[var9++];
         if (var11 != 0) {
            this.os_g.drawImage(this.bg_img[var11], 64 - var7, var8, (ImageObserver)null);
         }

         var11 = this.map_data[var9++];
         if (var11 != 0) {
            this.os_g.drawImage(this.bg_img[var11], 96 - var7, var8, (ImageObserver)null);
         }

         var11 = this.map_data[var9++];
         if (var11 != 0) {
            this.os_g.drawImage(this.bg_img[var11], 128 - var7, var8, (ImageObserver)null);
         }

         var11 = this.map_data[var9++];
         if (var11 != 0) {
            this.os_g.drawImage(this.bg_img[var11], 160 - var7, var8, (ImageObserver)null);
         }

         var11 = this.map_data[var9++];
         if (var11 != 0) {
            this.os_g.drawImage(this.bg_img[var11], 192 - var7, var8, (ImageObserver)null);
         }

         var11 = this.map_data[var9++];
         if (var11 != 0) {
            this.os_g.drawImage(this.bg_img[var11], 224 - var7, var8, (ImageObserver)null);
         }

         var11 = this.map_data[var9++];
         if (var11 != 0) {
            this.os_g.drawImage(this.bg_img[var11], 256 - var7, var8, (ImageObserver)null);
         }

         var11 = this.map_data[var9++];
         if (var11 != 0) {
            this.os_g.drawImage(this.bg_img[var11], 288 - var7, var8, (ImageObserver)null);
         }

         var11 = this.map_data[var9++];
         if (var11 != 0) {
            this.os_g.drawImage(this.bg_img[var11], 320 - var7, var8, (ImageObserver)null);
         }

         var11 = this.map_data[var9++];
         if (var11 != 0) {
            this.os_g.drawImage(this.bg_img[var11], 352 - var7, var8, (ImageObserver)null);
         }

         var11 = this.map_data[var9++];
         if (var11 != 0) {
            this.os_g.drawImage(this.bg_img[var11], 384 - var7, var8, (ImageObserver)null);
         }

         var9 += 147;
         var8 += 32;
         ++var10;
      } while(var10 <= 8);

   }
}
