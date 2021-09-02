import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.TextField;
import java.awt.image.ImageObserver;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class main extends Applet implements Runnable {
   int ap_width;
   int ap_height;
   Thread tread1;
   int interval1 = 90;
   c_cc cc_j;
   c_cc[] cc_t = new c_cc[64];
   c_cc cc_boss;
   int g_mode;
   int g_mode_c;
   int score;
   int highscore;
   int score_1up;
   int stage = 1;
   int stage_cc;
   int stage_tsc;
   int map_wx;
   int map_wy;
   int lives = 2;
   int level = 1;
   int j_left = 2;
   int t_kazu = 1;
   int c_1;
   int c_2;
   int c_2_o;
   int tr1_c;
   Image ggs_img;
   Graphics ggs_g;
   Image ggs_ptf_img;
   Image ggs_ptf_img1;
   Image ggs_ptf_img2;
   Image ggs_ptf_img3;
   Image ggs_pt1b_img;
   Image[] ggs_bg_img = new Image[100];
   Graphics ggs_pt1b_g;
   c_ggc ggc;
   int[] ggs_ptf_pix = new int[102400];
   int[] ggs_ptf_pix1 = new int[102400];
   int[] ggs_ptf_pix2 = new int[102400];
   int[] ggs_ptf_pix3 = new int[102400];
   int[] ggs_map_data = new int[25600];
   String[] ggs_map_nst = new String[160];
   Button again;
   Button send;
   TextField entryName;
   CGIcall cgi;
   static long oldscore = 0L;
   boolean mousePushed;
   private final String param_cfg_file = "Scorepath";
   private String m_cfgfile = "";
   private String param = "";
   private String URLName;

   public boolean keyDown(Event var1, int var2) {
      if (this.g_mode != 0 && this.g_mode != 210) {
         this.ggc.keyf_down(var2);
         if ((var2 == 115 || var2 == 83) && this.g_mode >= 50) {
            this.g_mode = 80;
         }

         return true;
      } else {
         return false;
      }
   }

   void shokika_3() {
      this.cc_j.c = 100;
      this.cc_j.x = 32;
      this.cc_j.y = 704;
      this.cc_j.vx = 0;
      this.cc_j.vy = 0;
      this.cc_j.c1 = 1;
      this.cc_j.c3 = 1;
      this.cc_j.pt = 1;
      this.cc_j.pth = 1;
      this.cc_j.ac = 0;
      this.stage_cc = 0;
      this.stage_tsc = 0;
      this.map_wx = 0;
      this.map_wy = 512;
      this.c_1 = 0;
      this.c_2 = 0;
      this.c_2_o = 0;
      this.tr1_c = 0;
      int var1 = 0;

      do {
         this.cc_t[var1].c = 0;
         this.cc_t[var1].af = false;
         ++var1;
      } while(var1 <= 63);

      this.cc_boss.c = 0;
      this.ggs_map_stage(this.stage);
   }

   public void ggs_init() {
      MediaTracker var1 = new MediaTracker(this);
      this.ggs_ptf_img = this.getImage(this.getDocumentBase(), "ggc.gif");
      var1.addImage(this.ggs_ptf_img, 0);

      try {
         var1.waitForID(0);
      } catch (InterruptedException var16) {
      }

      this.ggs_img = this.createImage(this.ap_width, this.ap_height);
      this.ggs_g = this.ggs_img.getGraphics();
      this.ggs_pt1b_img = this.createImage(32, 32);
      this.ggs_pt1b_g = this.ggs_pt1b_img.getGraphics();
      int var2 = 0;

      do {
         this.ggs_bg_img[var2] = this.createImage(32, 32);
         ++var2;
      } while(var2 <= 99);

      var2 = this.ggs_ptf_img.getWidth((ImageObserver)null);
      int var3 = this.ggs_ptf_img.getHeight((ImageObserver)null);
      PixelGrabber var4 = new PixelGrabber(this.ggs_ptf_img, 0, 0, var2, var3, this.ggs_ptf_pix, 0, var2);

      try {
         var4.grabPixels();
      } catch (InterruptedException var15) {
      }

      int var5 = 0;

      do {
         int var6 = 0;

         do {
            int var7 = var6 * 32;
            int var8 = var5 * 32;
            int var9 = 0;

            do {
               int var10 = 0;

               do {
                  int var11 = var7 + var10;
                  int var12 = var8 + var9;
                  int var13 = var7 + 31 - var10;
                  int var14 = var8 + var9;
                  this.ggs_ptf_pix1[var12 * 320 + var11] = this.ggs_ptf_pix[var14 * 320 + var13];
                  var13 = var7 + var10;
                  var14 = var8 + 31 - var9;
                  this.ggs_ptf_pix2[var12 * 320 + var11] = this.ggs_ptf_pix[var14 * 320 + var13];
                  var13 = var7 + 31 - var10;
                  var14 = var8 + 31 - var9;
                  this.ggs_ptf_pix3[var12 * 320 + var11] = this.ggs_ptf_pix[var14 * 320 + var13];
                  ++var10;
               } while(var10 <= 31);

               ++var9;
            } while(var9 <= 31);

            ++var6;
         } while(var6 <= 9);

         ++var5;
      } while(var5 <= 9);

      this.ggs_ptf_img1 = this.createImage(new MemoryImageSource(320, 320, this.ggs_ptf_pix1, 0, 320));
      this.ggs_ptf_img2 = this.createImage(new MemoryImageSource(320, 320, this.ggs_ptf_pix2, 0, 320));
      this.ggs_ptf_img3 = this.createImage(new MemoryImageSource(320, 320, this.ggs_ptf_pix3, 0, 320));
      this.ggc = new c_ggc(this.ggs_img, this.ggs_pt1b_img, this.ggs_ptf_img, this.ggs_ptf_img1, this.ggs_ptf_img2, this.ggs_ptf_img3, this.ggs_bg_img);
   }

   void boss_draw() {
      int var1 = this.cc_boss.x - 16 - this.map_wx;
      int var2 = this.cc_boss.y - 16 - this.map_wy;
      switch(this.cc_boss.pt) {
      case 1:
         this.ggc.pt_draw(58, var1, var2, 0);
         this.ggc.pt_draw(59, var1 + 32, var2, 0);
         this.ggc.pt_draw(66, var1, var2 + 32, 0);
         this.ggc.pt_draw(67, var1 + 32, var2 + 32, 0);
         return;
      case 2:
         this.ggc.pt_draw(59, var1, var2, 1);
         this.ggc.pt_draw(58, var1 + 32, var2, 1);
         this.ggc.pt_draw(67, var1, var2 + 32, 1);
         this.ggc.pt_draw(66, var1 + 32, var2 + 32, 1);
         return;
      case 3:
         this.ggc.pt_draw(58, var1, var2, 0);
         this.ggc.pt_draw(59, var1 + 32, var2, 0);
         this.ggc.pt_draw(68, var1, var2 + 32, 0);
         this.ggc.pt_draw(69, var1 + 32, var2 + 32, 0);
         return;
      case 4:
         this.ggc.pt_draw(59, var1, var2, 1);
         this.ggc.pt_draw(58, var1 + 32, var2, 1);
         this.ggc.pt_draw(69, var1, var2 + 32, 1);
         this.ggc.pt_draw(68, var1 + 32, var2 + 32, 1);
         return;
      case 5:
         this.ggc.pt_draw(56, var1, var2, 0);
         this.ggc.pt_draw(57, var1 + 32, var2, 0);
         this.ggc.pt_draw(66, var1, var2 + 32, 0);
         this.ggc.pt_draw(67, var1 + 32, var2 + 32, 0);
         return;
      case 6:
         this.ggc.pt_draw(57, var1, var2, 1);
         this.ggc.pt_draw(56, var1 + 32, var2, 1);
         this.ggc.pt_draw(67, var1, var2 + 32, 1);
         this.ggc.pt_draw(66, var1 + 32, var2 + 32, 1);
         return;
      case 7:
         this.ggc.pt_draw(56, var1, var2, 0);
         this.ggc.pt_draw(57, var1 + 32, var2, 0);
         this.ggc.pt_draw(68, var1, var2 + 32, 0);
         this.ggc.pt_draw(69, var1 + 32, var2 + 32, 0);
         return;
      case 8:
         this.ggc.pt_draw(57, var1, var2, 1);
         this.ggc.pt_draw(56, var1 + 32, var2, 1);
         this.ggc.pt_draw(69, var1, var2 + 32, 1);
         this.ggc.pt_draw(68, var1 + 32, var2 + 32, 1);
         return;
      case 9:
         this.ggc.pt_draw(48, var1, var2 + 32, 0);
         this.ggc.pt_draw(49, var1 + 32, var2 + 32, 0);
         return;
      case 10:
         this.ggc.pt_draw(49, var1, var2 + 32, 1);
         this.ggc.pt_draw(48, var1 + 32, var2 + 32, 1);
         return;
      default:
      }
   }

   public void paint(Graphics var1) {
      if (this.g_mode > 0) {
         var1.drawImage(this.ggs_img, 0, 0, (ImageObserver)null);
      }

   }

   int ran_n(int var1) {
      int var2 = (new Random()).nextInt() & 255;
      var2 = var2 * var1 / 256;
      return var2;
   }

   void g_main() {
      this.ggs_g.setColor(this.ggc.bc);
      this.ggs_g.fillRect(0, 0, 384, 256);
      if (this.stage == 7) {
         this.ggc.map_bg1_palet(86, 76 + this.c_2_o);
      } else if (this.stage == 2 || this.stage == 5) {
         this.ggc.map_bg1_palet(89, 78 + this.c_1);
      }

      if (this.ggc.keyf_tr1) {
         if (++this.tr1_c > 2) {
            this.tr1_c = 2;
         }
      } else {
         this.tr1_c = 0;
      }

      this.j_move();
      if (this.cc_boss.c > 0) {
         this.boss_move();
      }

      switch(this.c_2) {
      case 0:
         this.c_1 = 1;
         this.c_2 = 1;
         this.c_2_o = 0;
         this.ggc.bg_img[80] = this.ggc.bg_img[70];
         this.ggc.bg_img[83] = this.ggc.bg_img[74];
         if (this.stage_cc > 0) {
            ++this.stage_cc;
            if (this.stage_cc >= 12) {
               ++this.stage;
               this.g_mode = 90;
            }
         }
         break;
      case 1:
         this.c_1 = 0;
         this.c_2 = 2;
         this.c_2_o = 1;
         this.ggc.bg_img[80] = this.ggc.bg_img[71];
         this.ggc.bg_img[83] = this.ggc.bg_img[75];
         break;
      case 2:
         this.c_1 = 1;
         this.c_2 = 3;
         this.c_2_o = 1;
         this.ggc.bg_img[80] = this.ggc.bg_img[72];
         this.ggc.bg_img[83] = this.ggc.bg_img[74];
         if (this.stage_cc > 0) {
            ++this.stage_cc;
            if (this.stage_cc >= 12) {
               ++this.stage;
               this.g_mode = 90;
            }
         }
         break;
      case 3:
         this.c_1 = 0;
         this.c_2 = 0;
         this.c_2_o = 0;
         this.ggc.bg_img[80] = this.ggc.bg_img[73];
         this.ggc.bg_img[83] = this.ggc.bg_img[75];
      }

      this.ggc.map_draw(this.map_wx, this.map_wy);
      this.t_move();
      if (this.cc_boss.c > 0) {
         this.boss_draw();
      }

      if (this.cc_j.c >= 100) {
         this.cc_j.wx = this.cc_j.x - this.map_wx;
         this.cc_j.wy = this.cc_j.y - this.map_wy;
         this.ggc.pt_draw(this.cc_j.pt, this.cc_j.wx, this.cc_j.wy, this.cc_j.pth);
      }

      this.ggs_g.setColor(Color.blue);
      Font var1 = new Font("Dialog", 1, 12);
      this.ggs_g.setFont(var1);
      this.ggs_g.drawString("Score " + Integer.toString(this.score) + "    HighScore " + Integer.toString(this.highscore) + "    Left " + Integer.toString(this.j_left), 32, 16);
   }

   public boolean mouseUp(Event var1, int var2, int var3) {
      this.ggc.keyf_start = false;
      return true;
   }

   void score_add(int var1) {
      this.score += var1;
      if (this.score >= this.score_1up) {
         ++this.j_left;
         this.score_1up += 2000;
      }

   }

   void t_set_m(int var1, int var2, int var3, int var4) {
      int var5 = 28;

      do {
         if (this.cc_t[var5].c == 0) {
            this.cc_t[var5].c = var1;
            this.cc_t[var5].x = var2;
            this.cc_t[var5].y = var3;
            this.cc_t[var5].wx = -32;
            this.cc_t[var5].wy = -32;
            this.cc_t[var5].af = true;
            this.cc_t[var5].c6 = 0;
            switch(var1) {
            case 400:
               int var6 = this.cc_j.x - var2;
               int var7 = this.cc_j.y - var3;
               int var8 = (int)Math.sqrt((double)(var6 * var6 + var7 * var7));
               if (var8 < 64) {
                  this.cc_t[var5].c = 0;
                  this.cc_t[var5].af = false;
                  return;
               }

               this.cc_t[var5].vx = var6 * 100 / var8;
               this.cc_t[var5].vy = var7 * 100 / var8;
               return;
            case 410:
               double var9 = (double)var4;
               var9 = var9 * 3.14D / 180.0D;
               double var11 = Math.cos(var9) * 100.0D;
               double var13 = Math.sin(var9) * -100.0D;
               this.cc_t[var5].vx = (int)var11;
               this.cc_t[var5].vy = (int)var13;
               return;
            case 420:
               this.cc_t[var5].c1 = 0;
               return;
            default:
               return;
            }
         }

         --var5;
      } while(var5 >= 0);

   }

   public void destroy() {
      this.tread1.stop();
   }

   void j_move() {
      c_cc var10000;
      if (this.cc_j.c != 100) {
         if (this.cc_j.c == 200) {
            this.cc_j.c = 210;
            this.cc_j.vy = -200;
            var10000 = this.cc_j;
            var10000.y += (this.cc_j.vy + 5) / 10;
            this.cc_j.pt = 2;
            if (this.cc_j.c1 == 1) {
               this.cc_j.pth = 3;
            } else {
               this.cc_j.pth = 2;
            }
         } else if (this.cc_j.c == 210) {
            var10000 = this.cc_j;
            var10000.vy += 30;
            var10000 = this.cc_j;
            var10000.y += (this.cc_j.vy + 5) / 10;
            
            if (this.cc_j.wy >= 256) {
               this.cc_j.c2 = 0;
               if (this.j_left > 0) {
                  this.j_left += -1;
                  this.cc_j.c = 220;
               } else {
                  this.cc_j.c = 230;
               }
            }

            this.cc_j.pt = 2;
            if (this.cc_j.c1 == 1) {
               this.cc_j.pth = 3;
            } else {
               this.cc_j.pth = 2;
            }
         } else {
            if (this.cc_j.c == 220) {
               if (this.stage_cc == 0) {
                  ++this.cc_j.c2;
               }

               if (this.cc_j.c2 >= 10) {
                  this.g_mode = 90;
                  return;
               }
            } else if (this.cc_j.c == 230) {
               if (this.stage_cc == 0) {
                  ++this.cc_j.c2;
               }

               if (this.cc_j.c2 >= 10) {
                  this.g_mode = 200;
                  return;
               }
            } else {
               if (this.cc_j.c == 300) {
                  this.cc_j.pt = 1;
                  this.cc_j.pth = 1;
                  if (this.cc_j.wx >= 160) {
                     var10000 = this.cc_j;
                     var10000.wx -= 4;
                     if (this.cc_j.wx <= 160) {
                        this.cc_j.wx = 160;
                        this.cc_j.c = 310;
                     }
                  }

                  this.map_wx = this.cc_j.x - this.cc_j.wx;
                  return;
               }

               if (this.cc_j.c == 310) {
                  this.cc_j.pt = 3 + this.c_1;
                  this.cc_j.pth = 1;
                  var10000 = this.cc_j;
                  var10000.x += 6;
                  if (this.cc_j.x >= 4880) {
                     this.cc_j.x = 4880;
                     this.g_mode = 300;
                  }

                  this.cc_j.wx = this.cc_j.x - this.map_wx;
                  if (this.cc_j.wx > 160) {
                     this.cc_j.wx = 160;
                     this.map_wx = this.cc_j.x - 160;
                  }
               }
            }

         }
      } else {
         int var1 = (this.cc_j.x + 6) / 32;
         int var2 = (this.cc_j.x + 25) / 32;
         int var3 = (this.cc_j.y + 32) / 32;
         int var4 = var3 * 160;
         boolean var5;
         if (this.ggc.map_data[var4 + var1] < 90 && this.ggc.map_data[var4 + var2] < 90 && this.cc_j.c3 != 1) {
            var5 = false;
            this.cc_j.ac = 0;
         } else {
            var5 = true;
            if (this.tr1_c == 1) {
               if (this.cc_j.vx > -80 && this.cc_j.vx < 80) {
                  if (this.cc_j.vx == 0) {
                     this.cc_j.vy = -230;
                  } else {
                     this.cc_j.vy = -290;
                  }
               } else {
                  this.cc_j.vy = -340;
               }

               var5 = false;
               this.cc_j.ac = 0;
            }
         }

         if (this.ggc.keyf_right) {
            this.cc_j.c1 = 1;
            var10000 = this.cc_j;
            var10000.vx += 20;
            if (this.cc_j.vx > 80) {
               this.cc_j.vx = 80;
            }

            if (var5) {
               if (this.cc_j.ac == 0) {
                  this.cc_j.ac = 2;
               } else if (this.cc_j.ac == 2) {
                  this.cc_j.ac = 3;
               } else {
                  this.cc_j.ac = 2;
               }
            }
         } else if (this.ggc.keyf_left) {
            this.cc_j.c1 = -1;
            var10000 = this.cc_j;
            var10000.vx -= 20;
            if (this.cc_j.vx < -80) {
               this.cc_j.vx = -80;
            }

            if (var5) {
               if (this.cc_j.ac == 0) {
                  this.cc_j.ac = 2;
               } else if (this.cc_j.ac == 2) {
                  this.cc_j.ac = 3;
               } else {
                  this.cc_j.ac = 2;
               }
            }
         } else {
            if (this.cc_j.vx > 0) {
               var10000 = this.cc_j;
               var10000.vx -= 10;
               if (this.cc_j.vx < 0) {
                  this.cc_j.vx = 0;
               }
            } else if (this.cc_j.vx < 0) {
               var10000 = this.cc_j;
               var10000.vx += 10;
               if (this.cc_j.vx > 0) {
                  this.cc_j.vx = 0;
               }
            }

            if (this.cc_j.vx == 0) {
               this.cc_j.ac = 0;
            } else {
               this.cc_j.ac = 4;
            }
         }

         var10000 = this.cc_j;
         var10000.x += this.cc_j.vx / 10;
         var3 = this.cc_j.y / 32;
         var4 = var3 * 160;
         int var6 = (this.cc_j.y + 31) / 32;
         int var7 = var6 * 160;
         if (this.cc_j.vx > 0) {
            if (this.cc_j.x > 5088) {
               this.cc_j.x = 5088;
               this.cc_j.vx = 0;
            }

            var1 = (this.cc_j.x + 25) / 32;
            if (this.ggc.map_data[var4 + var1] >= 90 || this.ggc.map_data[var7 + var1] >= 90) {
               this.cc_j.x /= 32;
               this.cc_j.x = this.cc_j.x * 32 + 6;
               this.cc_j.vx = 0;
            }
         } else {
            if (this.cc_j.x < 0) {
               this.cc_j.x = 0;
               this.cc_j.vx = 0;
            }

            var1 = (this.cc_j.x + 6) / 32;
            if (this.ggc.map_data[var4 + var1] >= 90 || this.ggc.map_data[var7 + var1] >= 90) {
               this.cc_j.x /= 32;
               this.cc_j.x = this.cc_j.x * 32 + 32 - 6;
               this.cc_j.vx = 0;
            }
         }

         var10000 = this.cc_j;
         var10000.vy += 30;
         if (this.cc_j.vy > 200) {
            this.cc_j.vy = 200;
         }

         int var8 = this.cc_j.vy;
         if (var8 < -200) {
            var8 = -200;
         }

         var10000 = this.cc_j;
         var10000.y += (var8 + 5) / 10;
         if (this.cc_j.y < 1) {
            this.cc_j.y = 1;
            this.cc_j.vy = 0;
         }

         if (this.cc_j.y >= 768) {
            this.cc_j.c = 210;
            this.cc_j.c2 = 0;
         }

         var1 = (this.cc_j.x + 6) / 32;
         var2 = (this.cc_j.x + 25) / 32;
         int var9;
         int var10;
         int var11;
         if (this.cc_j.vy > 0) {
            var9 = (this.cc_j.y + 31) / 32;
            var10 = var9 * 160;
            if (this.ggc.map_data[var10 + var1] >= 90 || this.ggc.map_data[var10 + var2] >= 90) {
               var11 = this.cc_j.y / 32;
               this.cc_j.y = var11 * 32;
               this.cc_j.vy = 0;
               if (!var5) {
                  if (this.cc_j.vx == 0) {
                     this.cc_j.ac = 0;
                  } else {
                     this.cc_j.ac = 3;
                  }
               }

               var5 = true;
            }
         } else {
            var9 = this.cc_j.y / 32;
            var10 = var9 * 160;
            if (this.ggc.map_data[var10 + var1] >= 90 || this.ggc.map_data[var10 + var2] >= 90) {
               var11 = this.cc_j.y / 32;
               this.cc_j.y = var11 * 32 + 32;
               this.cc_j.vy = 30;
               int var12 = (this.cc_j.y + 32) / 32;
               int var13 = var12 * 160;
               if (this.ggc.map_data[var13 + var1] >= 90 || this.ggc.map_data[var13 + var2] >= 90) {
                  this.cc_j.vy = 30;
                  var5 = true;
               }
            }
         }

         if (this.cc_j.c1 == 1) {
            this.cc_j.pth = 1;
            if (var5) {
               this.cc_j.pt = 1 + this.cc_j.ac;
            } else if (this.cc_j.vy <= 20) {
               this.cc_j.pt = 2;
            } else {
               this.cc_j.pt = 3;
            }
         } else {
            this.cc_j.pth = 0;
            if (var5) {
               this.cc_j.pt = 1 + this.cc_j.ac;
            } else if (this.cc_j.vy <= 20) {
               this.cc_j.pt = 2;
            } else {
               this.cc_j.pt = 3;
            }
         }

         var9 = (this.cc_j.x + 16) / 32;
         var10 = (this.cc_j.y + 16) / 32;
         if (this.ggc.map_data[var10 * 160 + var9] != 0) {
            var11 = this.ggc.map_data[var10 * 160 + var9];
            if (var11 == 80) {
               this.ggc.map_bg1_put(0, var9, var10);
               this.score_add(10);
            } else if (var11 == 83) {
               this.ggc.map_bg1_put(0, var9, var10);
               this.score_add(500);
               this.stage_cc = 1;
            } else if (var11 == 84) {
               this.cc_j.c = 200;
            }
         }

         this.cc_j.wx = this.cc_j.x - this.map_wx;
         this.cc_j.wy = this.cc_j.y - this.map_wy;
         if (this.cc_j.wx > 160) {
            this.cc_j.wx = 160;
            this.map_wx = this.cc_j.x - 160;
         } else if (this.cc_j.wx < 64) {
            this.cc_j.wx = 64;
            this.map_wx = this.cc_j.x - 64;
         }

         if (this.cc_j.wy > 112) {
            this.cc_j.wy = 112;
            this.map_wy = this.cc_j.y - 112;
         } else if (this.cc_j.wy < 48) {
            this.cc_j.wy = 48;
            this.map_wy = this.cc_j.y - 48;
         }

         if (this.map_wx < 0) {
            this.map_wx = 0;
         } else if (this.map_wx > 4736) {
            this.map_wx = 4736;
         }

         if (this.map_wy < 0) {
            this.map_wy = 0;
         } else if (this.map_wy > 512) {
            this.map_wy = 512;
         }

         if (this.stage == 4) {
            if (this.stage_tsc == 0) {
               if (this.cc_j.x >= 4512) {
                  this.stage_tsc = 1;
                  this.map_wx = 4352;
                  this.cc_boss.c = 110;
               }
            } else if (this.stage_tsc >= 1) {
               this.map_wx = 4352;
               if (this.cc_j.x <= 4352) {
                  this.cc_j.x = 4352;
                  this.cc_j.vx = 0;
               } else if (this.cc_j.x >= 4704) {
                  this.cc_j.x = 4704;
                  this.cc_j.vx = 0;
               }

               if (this.stage_tsc == 1 && this.cc_boss.c == 0 && this.cc_j.y >= 672) {
                  this.ggc.map_bg1_put(83, 141, 19);
                  this.stage_tsc = 2;
               }
            }
         } else if (this.stage == 7) {
            if (this.stage_tsc == 0) {
               if (this.cc_j.x >= 4512) {
                  this.stage_tsc = 1;
                  this.map_wx = 4352;
                  this.cc_boss.c = 110;
               }
            } else if (this.stage_tsc == 1) {
               this.map_wx = 4352;
               if (this.cc_j.x <= 4352) {
                  this.cc_j.x = 4352;
                  this.cc_j.vx = 0;
               } else if (this.cc_j.x >= 4704) {
                  this.cc_j.x = 4704;
                  this.cc_j.vx = 0;
               }

               if (this.cc_boss.c == 0 && this.cc_j.y >= 672) {
                  this.cc_j.c = 300;
                  if (this.cc_j.x <= 4512) {
                     this.cc_j.c = 310;
                  }
               }
            }
         }

         this.cc_j.c3 = 0;
      }
   }

   public void update(Graphics var1) {
      this.paint(var1);
   }

   public boolean keyUp(Event var1, int var2) {
      if (this.g_mode != 0 && this.g_mode != 210) {
         this.ggc.keyf_up(var2);
         return true;
      } else {
         return false;
      }
   }

   void shokika_2() {
      if (this.score > this.highscore) {
         this.highscore = this.score;
      }

      this.score = 0;
      this.score_1up = 1000;
      this.stage = level;
      this.j_left = lives;
   }

   void t_move() {
      int var1 = 0;
      byte var2 = 0;
      int var3 = 0;

      do {
         if (this.cc_t[var3].c != 0) {
            label612: {
               if (this.cc_t[var3].af) {
                  if (this.cc_t[var3].x <= this.map_wx - 180) {
                     this.cc_t[var3].c = 0;
                     this.cc_t[var3].af = false;
                     break label612;
                  }
               } else {
                  if (this.cc_t[var3].c6 > this.map_wx) {
                     break label612;
                  }

                  this.cc_t[var3].af = true;
               }

               int var4 = this.cc_t[var3].c;
               int var5 = this.cc_t[var3].x;
               int var6 = this.cc_t[var3].y;
               int var7 = this.cc_t[var3].c1;
               int var8;
               int var9;
               int var10;
               int var11;
               int var12;
               int var13;
               int var14;
               int var15;
               c_cc var10000;
               int var16;
               int var17;
               int var18;
               int var19;
               int var20;
               int var21;
               int var23;
               int var24;
               int var25;
               int var32;
               int var46;
               switch(var4) {
               case 50:
                  var1 = 12;
                  var2 = 0;
                  ++var7;
                  if (var7 > 10) {
                     var4 = 0;
                     this.score_add(50);
                  }
                  break;
               case 55:
                  var1 = 12;
                  var2 = 1;
                  ++var7;
                  if (var7 > 10) {
                     var4 = 0;
                     this.score_add(50);
                  }
                  break;
               case 60:
                  var1 = 16;
                  var2 = 2;
                  var6 += 10;
                  if (var6 - this.map_wy >= 256) {
                     var4 = 0;
                     this.score_add(80);
                  }
                  break;
               case 65:
                  var1 = 16;
                  var2 = 3;
                  var6 += 10;
                  if (var6 - this.map_wy >= 256) {
                     var4 = 0;
                     this.score_add(80);
                  }
                  break;
               case 70:
                  var1 = 19;
                  var2 = 0;
                  ++var7;
                  if (var7 > 10) {
                     var4 = 0;
                     this.score_add(80);
                  }
                  break;
               case 75:
                  var1 = 19;
                  var2 = 1;
                  ++var7;
                  if (var7 > 10) {
                     var4 = 0;
                     this.score_add(80);
                  }
                  break;
               case 80:
                  var1 = 8;
                  var2 = 0;
                  ++var7;
                  if (var7 == 10) {
                     var10000 = this.cc_t[var3];
                     var10000.tai += -1;
                     if (this.cc_t[var3].tai <= 0) {
                        var4 = 0;
                        this.score_add(100);
                     }
                  }

                  if (var7 >= 26) {
                     var4 = 140;
                  }
                  break;
               case 85:
                  var1 = 8;
                  var2 = 1;
                  ++var7;
                  if (var7 == 10) {
                     var10000 = this.cc_t[var3];
                     var10000.tai += -1;
                     if (this.cc_t[var3].tai <= 0) {
                        var4 = 0;
                        this.score_add(100);
                     }
                  }

                  if (var7 >= 26) {
                     var4 = 150;
                  }
                  break;
               case 90:
                  var1 = 29;
                  var2 = 0;
                  ++var7;
                  if (var7 > 10) {
                     var4 = 0;
                     this.score_add(200);
                  }
                  break;
               case 95:
                  var1 = 29;
                  var2 = 1;
                  ++var7;
                  if (var7 > 10) {
                     var4 = 0;
                     this.score_add(200);
                  }
                  break;
               case 100:
                  var1 = 10 + this.c_2_o;
                  var2 = 0;
                  var8 = this.ggc.map_bg1_get(var5 + 31, var6 + 32);
                  if (var8 < 90) {
                     var6 += 6;
                     var9 = this.ggc.map_bg1_get(var5 + 16, var6 + 31);
                     if (var9 >= 90) {
                        var10 = var6 / 32;
                        var6 = var10 * 32;
                     }

                     if (var6 >= 768) {
                        var4 = 0;
                     }
                  } else {
                     var5 -= 4;
                     if (var5 <= -32) {
                        var4 = 0;
                     }

                     var9 = this.ggc.map_bg1_get(var5, var6 + 16);
                     if (var9 >= 90) {
                        var10 = var5 / 32;
                        var5 = var10 * 32 + 32;
                        var4 = 110;
                     }
                  }
                  break;
               case 110:
                  var1 = 10 + this.c_2_o;
                  var2 = 1;
                  var9 = this.ggc.map_bg1_get(var5, var6 + 32);
                  if (var9 < 90) {
                     var6 += 6;
                     var10 = this.ggc.map_bg1_get(var5 + 16, var6 + 31);
                     if (var10 >= 90) {
                        var11 = var6 / 32;
                        var6 = var11 * 32;
                     }

                     if (var6 >= 768) {
                        var4 = 0;
                     }
                  } else {
                     var5 += 4;
                     if (var5 >= 5120) {
                        var4 = 0;
                     }

                     var10 = this.ggc.map_bg1_get(var5 + 31, var6 + 16);
                     if (var10 >= 90) {
                        var11 = var5 / 32;
                        var5 = var11 * 32;
                        var4 = 100;
                     }
                  }
                  break;
               case 120:
                  var1 = 10 + this.c_2_o;
                  var2 = 0;
                  var5 -= 4;
                  if (var5 <= -32) {
                     var4 = 0;
                  }

                  var10 = this.ggc.map_bg1_get(var5, var6 + 16);
                  if (var10 >= 90) {
                     var11 = var5 / 32;
                     var5 = var11 * 32 + 32;
                     var4 = 130;
                  }

                  var10 = this.ggc.map_bg1_get(var5, var6 + 32);
                  if (var10 < 90) {
                     var11 = var5 / 32;
                     var5 = var11 * 32 + 32;
                     var4 = 130;
                  }
                  break;
               case 130:
                  var1 = 10 + this.c_1;
                  var2 = 1;
                  var5 += 4;
                  if (var5 >= 5120) {
                     var4 = 0;
                  }

                  var11 = this.ggc.map_bg1_get(var5 + 31, var6 + 16);
                  if (var11 >= 90) {
                     var12 = var5 / 32;
                     var5 = var12 * 32;
                     var4 = 120;
                  }

                  var11 = this.ggc.map_bg1_get(var5 + 31, var6 + 32);
                  if (var11 < 90) {
                     var12 = var5 / 32;
                     var5 = var12 * 32;
                     var4 = 120;
                  }
                  break;
               case 140:
                  var1 = 6 + this.c_1;
                  var2 = 0;
                  var5 -= 6;
                  if (var5 <= -32) {
                     var4 = 0;
                  }

                  var12 = this.ggc.map_bg1_get(var5, var6 + 16);
                  if (var12 >= 90) {
                     var13 = var5 / 32;
                     var5 = var13 * 32 + 32;
                     var4 = 150;
                  }

                  var12 = this.ggc.map_bg1_get(var5, var6 + 32);
                  if (var12 < 90) {
                     var13 = var5 / 32;
                     var5 = var13 * 32 + 32;
                     var4 = 150;
                  }
                  break;
               case 150:
                  var1 = 6 + this.c_2_o;
                  var2 = 1;
                  var5 += 6;
                  if (var5 >= 5120) {
                     var4 = 0;
                  }

                  var13 = this.ggc.map_bg1_get(var5 + 31, var6 + 16);
                  if (var13 >= 90) {
                     var14 = var5 / 32;
                     var5 = var14 * 32;
                     var4 = 140;
                  }

                  var13 = this.ggc.map_bg1_get(var5 + 31, var6 + 32);
                  if (var13 < 90) {
                     var14 = var5 / 32;
                     var5 = var14 * 32;
                     var4 = 140;
                  }
                  break;
               case 200:
                  var1 = 13 + this.c_2_o;
                  var2 = 0;
                  var14 = this.ggc.map_bg1_get(var5 + 31, var6 + 32);
                  if (var14 < 90) {
                     var6 += 6;
                     var15 = this.ggc.map_bg1_get(var5 + 16, var6 + 31);
                     if (var15 >= 90) {
                        var16 = var6 / 32;
                        var6 = var16 * 32;
                     }

                     if (var6 >= 768) {
                        var4 = 0;
                     }
                  } else {
                     var5 -= 6;
                     if (var5 <= -32) {
                        var4 = 0;
                     }

                     var15 = this.ggc.map_bg1_get(var5, var6 + 16);
                     if (var15 >= 90) {
                        var16 = var5 / 32;
                        var5 = var16 * 32 + 32;
                        var4 = 210;
                     }
                  }
                  break;
               case 210:
                  var1 = 13 + this.c_2_o;
                  var2 = 0;
                  var15 = this.ggc.map_bg1_get(var5, var6 + 32);
                  if (var15 < 90) {
                     var6 += 6;
                     var16 = this.ggc.map_bg1_get(var5 + 16, var6 + 31);
                     if (var16 >= 90) {
                        var17 = var6 / 32;
                        var6 = var17 * 32;
                     }

                     if (var6 >= 768) {
                        var4 = 0;
                     }
                  } else {
                     var5 += 6;
                     if (var5 >= 5120) {
                        var4 = 0;
                     }

                     var16 = this.ggc.map_bg1_get(var5 + 31, var6 + 16);
                     if (var16 >= 90) {
                        var17 = var5 / 32;
                        var5 = var17 * 32;
                        var4 = 200;
                     }
                  }
                  break;
               case 220:
                  var1 = 13 + this.c_2_o;
                  var2 = 0;
                  var5 -= 6;
                  if (var5 <= -32) {
                     var4 = 0;
                  }

                  var16 = this.ggc.map_bg1_get(var5, var6 + 16);
                  if (var16 >= 90) {
                     var17 = var5 / 32;
                     var5 = var17 * 32 + 32;
                     var4 = 230;
                  }

                  var16 = this.ggc.map_bg1_get(var5, var6 + 32);
                  if (var16 < 90) {
                     var17 = var5 / 32;
                     var5 = var17 * 32 + 32;
                     var4 = 230;
                  }
                  break;
               case 230:
                  var1 = 13 + this.c_2_o;
                  var2 = 0;
                  var5 += 6;
                  if (var5 >= 5120) {
                     var4 = 0;
                  }

                  var17 = this.ggc.map_bg1_get(var5 + 31, var6 + 16);
                  if (var17 >= 90) {
                     var18 = var5 / 32;
                     var5 = var18 * 32;
                     var4 = 220;
                  }

                  var17 = this.ggc.map_bg1_get(var5 + 31, var6 + 32);
                  if (var17 < 90) {
                     var18 = var5 / 32;
                     var5 = var18 * 32;
                     var4 = 220;
                  }
                  break;
               case 300:
                  var1 = 15 + this.c_2_o;
                  if (this.cc_j.x < var5) {
                     var2 = 0;
                  } else {
                     var2 = 1;
                  }

                  if (var6 <= var7) {
                     var10000 = this.cc_t[var3];
                     var10000.vy += 10;
                     if (this.cc_t[var3].vy > 60) {
                        this.cc_t[var3].vy = 60;
                     }
                  }

                  if (var6 >= this.cc_t[var3].c2) {
                     var10000 = this.cc_t[var3];
                     var10000.vy -= 10;
                     if (this.cc_t[var3].vy < -60) {
                        this.cc_t[var3].vy = -60;
                     }
                  }

                  var18 = this.cc_t[var3].vy / 10;
                  var6 += var18;
                  break;
               case 350:
                  var2 = 0;
                  var1 = 17;
                  if (var7 < 10) {
                     var1 = 17 + this.c_2_o;
                     ++var7;
                     if (var7 == 10) {
                        this.cc_t[var3].vy = -260;
                     }
                  } else {
                     var5 -= 4;
                     if (var5 <= -32) {
                        var4 = 0;
                     }

                     var10000 = this.cc_t[var3];
                     var10000.vy += 30;
                     var19 = this.cc_t[var3].vy / 10;
                     var6 += var19;
                     var20 = this.ggc.map_bg1_get(var5 + 16, var6 + 31);
                     if (var20 >= 90) {
                        var21 = var6 / 32;
                        var6 = var21 * 32;
                        this.cc_t[var3].vy = 0;
                        var7 = 0;
                     }

                     if (var6 >= 768) {
                        var4 = 0;
                     }

                     if (this.cc_t[var3].vy < 0) {
                        var1 = 18;
                     }
                  }
                  break;
               case 360:
                  var5 -= 8;
                  if (var5 <= -32) {
                     var4 = 0;
                  }

                  var19 = this.ggc.map_bg1_get(var5, var6 + 31);
                  if (var19 >= 90) {
                     var20 = var5 / 32;
                     var5 = var20 * 32 + 32;
                     var4 = 365;
                  }

                  var10000 = this.cc_t[var3];
                  var10000.vy += 30;
                  var20 = this.cc_t[var3].vy / 10;
                  var6 += var20;
                  var19 = this.ggc.map_bg1_get(var5 + 16, var6 + 31);
                  if (var19 >= 90) {
                     var21 = var6 / 32;
                     var6 = var21 * 32;
                     this.cc_t[var3].vy = -150;
                     var7 = 0;
                  }

                  var2 = 0;
                  if (this.cc_t[var3].vy < 0) {
                     var1 = 18;
                  } else {
                     var1 = 17;
                  }

                  if (var6 >= 768) {
                     var4 = 0;
                  }
                  break;
               case 365:
                  var5 += 8;
                  if (var5 >= 5120) {
                     var4 = 0;
                  }

                  var21 = this.ggc.map_bg1_get(var5 + 31, var6 + 31);
                  int var22;
                  if (var21 >= 90) {
                     var22 = var5 / 32;
                     var5 = var22 * 32;
                     var4 = 360;
                  }

                  var10000 = this.cc_t[var3];
                  var10000.vy += 30;
                  var22 = this.cc_t[var3].vy / 10;
                  var6 += var22;
                  var21 = this.ggc.map_bg1_get(var5 + 16, var6 + 31);
                  if (var21 >= 90) {
                     var23 = var6 / 32;
                     var6 = var23 * 32;
                     this.cc_t[var3].vy = -150;
                     var7 = 0;
                  }

                  var2 = 1;
                  if (this.cc_t[var3].vy < 0) {
                     var1 = 18;
                  } else {
                     var1 = 17;
                  }

                  if (var6 >= 768) {
                     var4 = 0;
                  }
                  break;
               case 370:
                  var1 = 17;
                  if (this.cc_j.x < var5) {
                     var2 = 0;
                  } else {
                     var2 = 1;
                  }

                  if (var7 < 15) {
                     var1 = 17 + this.c_2_o;
                     ++var7;
                     if (var7 == 15) {
                        this.cc_t[var3].vy = -260;
                     }
                  } else {
                     var23 = this.cc_t[var3].vy;
                     var10000 = this.cc_t[var3];
                     var10000.vy += 30;
                     if (var23 <= 0 && this.cc_t[var3].vy > 0) {
                        var23 = Math.abs(this.cc_j.x - var5);
                        if (var23 > 30) {
                           this.t_set_m(400, var5, var6, 0);
                        }
                     }

                     var23 = this.cc_t[var3].vy / 10;
                     var6 += var23;
                     var24 = this.ggc.map_bg1_get(var5 + 16, var6 + 31);
                     if (var24 >= 90) {
                        var25 = var6 / 32;
                        var6 = var25 * 32;
                        this.cc_t[var3].vy = 0;
                        var7 = 0;
                     }

                     if (this.cc_t[var3].vy < 0) {
                        var1 = 18;
                     }
                  }
                  break;
               case 380:
                  var1 = 27;
                  var2 = 0;
                  if (var7 == 0) {
                     if (this.cc_j.x >= var5 - 192) {
                        var7 = 1;
                     }
                  } else {
                     ++var7;
                     if (var7 >= 28) {
                        var4 = 390;
                     }
                  }
                  break;
               case 390:
                  var1 = 27 + this.c_1;
                  var2 = 1;
                  var23 = this.ggc.map_bg1_get(var5, var6 + 32);
                  if (var23 < 90) {
                     var1 = 27;
                     var6 += 8;
                     var24 = this.ggc.map_bg1_get(var5 + 16, var6 + 31);
                     if (var24 >= 90) {
                        var25 = var6 / 32;
                        var6 = var25 * 32;
                     }

                     if (var6 >= 768) {
                        var4 = 0;
                     }
                  } else {
                     var5 += 10;
                     if (var5 >= 5120) {
                        var4 = 0;
                     }

                     var24 = this.ggc.map_bg1_get(var5 + 31, var6 + 16);
                     if (var24 >= 90) {
                        var25 = var5 / 32;
                        var5 = var25 * 32;
                        var4 = 100;
                     }
                  }
                  break;
               case 400:
                  var1 = 20 + this.c_1;
                  var2 = 0;
                  var24 = this.cc_t[var3].vx / 10;
                  var5 += var24;
                  var24 = this.cc_t[var3].vy / 10;
                  var6 += var24;
                  var25 = this.ggc.map_bg1_get(var5 + 16, var6 + 16);
                  int var26 = var5 - this.map_wx;
                  int var27 = var6 - this.map_wy;
                  if (var25 >= 90 || var26 <= -32 || var26 >= 384 || var27 <= -32 || var27 >= 256) {
                     var4 = 0;
                  }
                  break;
               case 410:
                  if (this.stage == 7) {
                     var1 = 37;
                  } else {
                     var1 = 36;
                  }

                  var2 = 0;
                  int var28 = this.cc_t[var3].vx / 10;
                  var5 += var28;
                  var28 = this.cc_t[var3].vy / 10;
                  var6 += var28;
                  int var29 = this.ggc.map_bg1_get(var5 + 16, var6 + 16);
                  int var30 = var5 - this.map_wx;
                  int var31 = var6 - this.map_wy;
                  if (var29 >= 90 || var30 <= -32 || var30 >= 384 || var31 <= -32 || var31 >= 256) {
                     var4 = 0;
                  }
                  break;
               case 420:
                  if (this.stage == 7) {
                     var1 = 37;
                  } else {
                     var1 = 36;
                  }

                  var2 = 0;
                  ++var7;
                  if (var7 <= 4) {
                     var6 -= 8;
                  }

                  if (var7 == 4) {
                     var32 = 0;

                     do {
                        this.t_set_m(410, var5, var6, var32);
                        var32 += 20;
                     } while(var32 <= 340);
                  }

                  if (var7 >= 5) {
                     var4 = 0;
                  }
                  break;
               case 430:
                  var1 = 38;
                  var2 = 0;
                  var5 -= 10;
                  var32 = var5 - this.map_wx;
                  if (var32 <= -32) {
                     var4 = 0;
                  }
                  break;
               case 440:
                  var1 = 38;
                  var2 = 1;
                  var5 += 10;
                  int var33 = var5 - this.map_wx;
                  if (var33 >= 384) {
                     var4 = 0;
                  }
                  break;
               case 450:
                  var1 = 24;
                  var2 = 0;
                  var7 += 15;
                  if (var7 >= 360) {
                     var7 -= 360;
                  }

                  double var34 = (double)var7;
                  var34 = var34 * 3.14D / 180.0D;
                  double var36 = Math.cos(var34) * 90.0D;
                  double var38 = Math.sin(var34) * -90.0D;
                  var5 = this.cc_t[var3].vx + (int)var36;
                  var6 = this.cc_t[var3].vy + (int)var38;
                  break;
               case 460:
                  var1 = 24;
                  var2 = 0;
                  var7 -= 15;
                  if (var7 < 0) {
                     var7 += 360;
                  }

                  double var40 = (double)var7;
                  var40 = var40 * 3.14D / 180.0D;
                  double var42 = Math.cos(var40) * 90.0D;
                  double var44 = Math.sin(var40) * -90.0D;
                  var5 = this.cc_t[var3].vx + (int)var42;
                  var6 = this.cc_t[var3].vy + (int)var44;
                  break;
               case 470:
                  var1 = 22 + this.c_1;
                  var2 = 0;
                  if (var7 < 6) {
                     ++var7;
                     if (var7 >= 6) {
                        this.cc_t[var3].vy = -360;
                     }
                  } else if (var7 >= 6) {
                     var46 = this.cc_t[var3].vy + 30;
                     if (var46 > 270) {
                        var46 = 270;
                     }

                     this.cc_t[var3].vy = var46;
                     if (var46 < -270) {
                        var46 = -270;
                     }

                     var46 /= 10;
                     var6 += var46;
                     if (var6 >= 768) {
                        var6 = 768;
                        var7 = 0;
                     }

                     if (var46 > 0) {
                        var1 = 22 + this.c_1;
                        var2 = 2;
                     }
                  }
                  break;
               case 500:
                  if (var5 <= var7) {
                     var10000 = this.cc_t[var3];
                     var10000.vx += 10;
                     if (this.cc_t[var3].vx > 40) {
                        this.cc_t[var3].vx = 40;
                     }
                  }

                  if (var5 >= this.cc_t[var3].c2) {
                     var10000 = this.cc_t[var3];
                     var10000.vx -= 10;
                     if (this.cc_t[var3].vx < -40) {
                        this.cc_t[var3].vx = -40;
                     }
                  }

                  var46 = this.cc_t[var3].vx / 10;
                  var5 += var46;
                  break;
               case 510:
                  if (var6 <= var7) {
                     var10000 = this.cc_t[var3];
                     var10000.vy += 10;
                     if (this.cc_t[var3].vy > 40) {
                        this.cc_t[var3].vy = 40;
                     }
                  }

                  if (var6 >= this.cc_t[var3].c2) {
                     var10000 = this.cc_t[var3];
                     var10000.vy -= 10;
                     if (this.cc_t[var3].vy < -40) {
                        this.cc_t[var3].vy = -40;
                     }
                  }

                  int var47;
                  if (this.cc_j.c == 100) {
                     var47 = Math.abs(var5 - this.cc_j.x);
                     int var48 = this.cc_j.y - var6;
                     if (var47 < 42 && var48 >= -32 && var48 < -10) {
                        int var49 = this.cc_t[var3].vy / 10;
                        var10000 = this.cc_j;
                        var10000.y += var49;
                     }
                  }

                  var47 = this.cc_t[var3].vy / 10;
                  var6 += var47;
               }

               if (var4 == 0) {
                  this.cc_t[var3].c = 0;
                  this.cc_t[var3].af = false;
               } else {
                  if (this.cc_j.c == 100) {
                     if (var4 >= 100 && var4 <= 290) {
                        var8 = Math.abs(var5 - this.cc_j.x);
                        var9 = var6 - this.cc_j.y;
                        if (var8 < 30 && var9 < 20 && var9 > -32) {
                           if (this.cc_j.vy > 0 && var8 < 27 && var4 < 200) {
                              var7 = 0;
                              if (var4 != 100 && var4 != 120) {
                                 if (var4 != 110 && var4 != 130) {
                                    if (var4 == 140) {
                                       var4 = 80;
                                    } else {
                                       var4 = 85;
                                    }
                                 } else {
                                    var4 = 55;
                                 }
                              } else {
                                 var4 = 50;
                              }

                              this.cc_j.y = var6 - 18;
                              this.cc_j.wy = this.cc_j.y - this.map_wy;
                              this.cc_j.vy = -170;
                           } else {
                              this.cc_j.c = 200;
                           }
                        }
                     } else if (var4 >= 300 && var4 <= 390) {
                        var8 = Math.abs(var5 - this.cc_j.x);
                        var9 = Math.abs(var6 - this.cc_j.y);
                        if (var8 < 30 && var9 < 32) {
                           if (this.cc_j.vy > 0 && var8 < 27 && var4 <= 390) {
                              var7 = 0;
                              if (var4 == 300) {
                                 if (this.cc_j.x < var5) {
                                    var4 = 60;
                                 } else {
                                    var4 = 65;
                                 }
                              } else if (var4 >= 350 && var4 <= 370) {
                                 if (var4 == 370) {
                                    if (this.cc_j.x <= var5) {
                                       var4 = 70;
                                    } else {
                                       var4 = 75;
                                    }
                                 } else if (var4 == 365) {
                                    var4 = 75;
                                 } else {
                                    var4 = 70;
                                 }
                              } else if (var4 == 380) {
                                 var4 = 90;
                              } else {
                                 var4 = 95;
                              }

                              this.cc_j.y = var6 - 18;
                              this.cc_j.wy = this.cc_j.y - this.map_wy;
                              this.cc_j.vy = -170;
                           } else {
                              this.cc_j.c = 200;
                           }
                        }
                     } else if (var4 >= 400 && var4 <= 490) {
                        var8 = Math.abs(var5 - this.cc_j.x);
                        var9 = Math.abs(var6 - this.cc_j.y);
                        if (var8 < 26 && var9 < 26) {
                           this.cc_j.c = 200;
                        }
                     } else if (var4 >= 500 && var4 <= 590) {
                        var8 = Math.abs(var5 - this.cc_j.x);
                        var9 = this.cc_j.y - var6;
                        if (var8 < 42 && var9 > -32 && var9 < 16) {
                           if (var9 < -10) {
                              this.cc_j.y = var6 - 32;
                              this.cc_j.wy = this.cc_j.y - this.map_wy;
                              if (this.cc_j.vy > 0) {
                                 this.cc_j.vy = 0;
                              }

                              this.cc_j.c3 = 1;
                              var10 = this.cc_t[var3].vx / 10;
                              var10000 = this.cc_j;
                              var10000.x += var10;
                           } else if (var9 < 0) {
                              if (var5 < this.cc_j.x) {
                                 this.cc_j.x = var5 + 42;
                                 this.cc_j.vx = 0;
                              } else {
                                 this.cc_j.x = var5 - 42;
                                 this.cc_j.vx = 0;
                              }
                           } else {
                              this.cc_j.y = var6 + 16;
                              this.cc_j.vy = 40;
                           }
                        }
                     }
                  }

                  if (var4 >= 500) {
                     this.ggc.pt_draw(9, var5 - this.map_wx - 16, var6 - this.map_wy, 0);
                     this.ggc.pt_draw(9, var5 - this.map_wx + 16, var6 - this.map_wy, 1);
                  } else {
                     this.ggc.pt_draw(var1, var5 - this.map_wx, var6 - this.map_wy, var2);
                  }

                  this.cc_t[var3].c = var4;
                  this.cc_t[var3].x = var5;
                  this.cc_t[var3].y = var6;
                  this.cc_t[var3].c1 = var7;
               }
            }
         }

         ++var3;
      } while(var3 <= 28);

   }

   public long GetLastScore() {
      return oldscore;
   }

   public boolean mouseDown(Event var1, int var2, int var3) {
      this.ggc.keyf_start = true;
      if (this.g_mode >= 50 && this.g_mode != 210) {
         this.g_mode = 80;
      }

      return true;
   }

   public void ggs_map_stage(int var1) {
      String var2 = "........................................";
      String var3 = "3333333333333333333333333333333333333333";
      String var4 = "4444444444444444444444444444444444444444";
      this.t_kazu = 0;
      String var5 = "";
      int var6 = 1;

      do {
         var5 = var5 + ".";
         ++var6;
      } while(var6 <= 160);

      var6 = 0;

      int var7;
      do {
         var7 = 0;

         do {
            this.ggs_map_data[var6 * 160 + var7] = 0;
            ++var7;
         } while(var7 <= 159);

         this.ggs_map_nst[var6] = var5;
         ++var6;
      } while(var6 <= 159);

      String[] var10000;
      if (var1 == 1) {
         this.ggc.back_color(0, 255, 255);
         this.ggs_map_nst[0] = var2;
         this.ggs_map_nst[1] = var2;
         this.ggs_map_nst[2] = var2;
         this.ggs_map_nst[3] = var2;
         this.ggs_map_nst[4] = var2;
         this.ggs_map_nst[5] = var2;
         this.ggs_map_nst[6] = var2;
         this.ggs_map_nst[7] = var2;
         this.ggs_map_nst[8] = var2;
         this.ggs_map_nst[9] = var2;
         this.ggs_map_nst[10] = var2;
         this.ggs_map_nst[11] = var2;
         this.ggs_map_nst[12] = "................bc......................";
         this.ggs_map_nst[13] = var2;
         this.ggs_map_nst[14] = var2;
         this.ggs_map_nst[15] = "....................a...................";
         this.ggs_map_nst[16] = "....................1...................";
         this.ggs_map_nst[17] = "..bc.......bc..............bc.......bc..";
         this.ggs_map_nst[18] = ".................a..a..a................";
         this.ggs_map_nst[19] = ".......aa.......11..1..11...............";
         this.ggs_map_nst[20] = "......1111.....................1..1.....";
         this.ggs_map_nst[21] = "..............................11..11....";
         this.ggs_map_nst[22] = ".Z...............A.........A.111..111...";
         this.ggs_map_nst[23] = "2222222222222222222222222222222222222222";
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + var2;
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + var2;
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + var2;
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + var2;
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + var2;
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + var2;
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + var2;
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + var2;
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + var2;
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + var2;
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + var2;
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + var2;
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + var2;
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + var2;
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + "...bc.....bc....bc.....bc...............";
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + var2;
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + var2;
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + ".................................bc.a..a";
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + ".....1111..111111..1111..aaa............";
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + ".....................................C..";
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + ".11..a..a..a....a..a..a..111........1111";
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + var2;
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + "..........B...........B.............a..a";
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + "2222222222222222222222222222222..2222222";
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + var2;
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + var2;
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + var2;
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + var2;
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + var2;
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + var2;
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + var2;
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + var2;
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + var2;
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + var2;
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + "..................................bc....";
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + var2;
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + "........................bc..............";
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + "...............bc.......................";
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + var2;
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + "............................1...........";
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + "............................1......bc...";
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + "...bc.................C.....1...........";
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + "....................11111...1...........";
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + "................E...........1...........";
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + "............111111..........1...........";
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + "............................111111......";
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + ".......aa....aa...a.a.a.a...1..aaa.C....";
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + "222....22222222...2222222..22..22222..22";
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + var2;
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + "....aaaaaaa" + var2;
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + "....aaaaaaa" + var2;
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + "....aaaaaaa......bc.....................";
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + "....11...11" + var2;
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + var2;
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + ".......C" + var2;
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + "......111.................bc............";
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + "..................bc....................";
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + var2;
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + "...1......bc............................";
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + var2;
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + var2;
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + "........1............bc.................";
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + "......111......................bc.......";
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + "........1" + var2;
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + "........1..aa.................A.........";
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + ".....C.a1.......bc............1.....bc..";
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + "..1111.a1...................A11.........";
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + "..1....a1..11...............111.........";
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + "..1....a1.........E........1111.....d...";
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + "111.....1.....11111111....11111.........";
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + "aaa.C...1.....aa.aa.aa...111111.........";
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + "222222222.....22222222222222222222222222";
      } else if (var1 == 2) {
         this.ggc.back_color(1, 1, 1);
         this.ggs_map_nst[0] = var3;
         this.ggs_map_nst[1] = var3;
         this.ggs_map_nst[2] = var3;
         this.ggs_map_nst[3] = var3;
         this.ggs_map_nst[4] = var3;
         this.ggs_map_nst[5] = var3;
         this.ggs_map_nst[6] = var3;
         this.ggs_map_nst[7] = var3;
         this.ggs_map_nst[8] = var3;
         this.ggs_map_nst[9] = var3;
         this.ggs_map_nst[10] = var3;
         this.ggs_map_nst[11] = var3;
         this.ggs_map_nst[12] = var3;
         this.ggs_map_nst[13] = var3;
         this.ggs_map_nst[14] = "333333333333333333333333333.............";
         this.ggs_map_nst[15] = "333333333333333333333333333.............";
         this.ggs_map_nst[16] = "333333333333333333333333333............a";
         this.ggs_map_nst[17] = "333333333333333333333333333.............";
         this.ggs_map_nst[18] = "333333............333333333.............";
         this.ggs_map_nst[19] = "333333.j..j..j..j.333333333............3";
         this.ggs_map_nst[20] = "..................333333333.....3.......";
         this.ggs_map_nst[21] = ".Z...........A...A.....B...........3....";
         this.ggs_map_nst[22] = "33333333333333333333333333333eeeeeeeeeee";
         this.ggs_map_nst[23] = var3;
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + var3;
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + "333333333333aaaa...................33333";
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + "333333333333aaaa333333333333333....33333";
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + "333333333333aaaa333333333333333....33333";
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + "333333333333333333333333333333333..33333";
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + "333333333333333333333333333333333..33333";
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + "33333333333333333333333..........C.a.a.a";
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + "333333333333333333333.....33333333333333";
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + "3333333333333333333.....3333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + "333333aaa.............333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + "333333aaa...........33333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + "3333333333333...333333333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + "3333333333333...333333333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + "3333333333333...333333333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + "................333333333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + "................333333333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + "..............H.333333333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + "................333333333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + ".....a..........333333333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + "................333333333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + "..........C.....333333333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + ".....3...333....333333333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + "eeeeeeeeeeeeeeee333333333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + var3;
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + var3;
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + var3;
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + var3;
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + var3;
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + var3;
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + var3;
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + ".a....3333333333333333333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + "33333a3333333333333333333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + "33333a3333333333333333333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + "33333a333333333333333333333333333333333.";
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + "33333.333333333333333333333333333333333.";
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + "33333a33333333333333333333333333333333..";
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + "33333a33333333333333333333333333333333.3";
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + "33333.3333333333333333333333333333333..3";
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + "33333a3333333.........................33";
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + "33333a3333333.........................33";
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + "33333a3333333........................333";
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + "33333.................................33";
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + "33333.j.j.j.j.........................33";
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + "33333......................33....C....33";
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + "33333..........................333....33";
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + "33333333333333......F.................C.";
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + "33333333333333eeeeeeeeeeeeeeeeeeeeeee333";
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + var3;
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + var3;
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + var3;
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + var3;
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + var3;
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + var3;
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + var3;
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + "3333aaaaaa" + var3;
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + "3333aaaaaa" + var3;
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + "3333......" + var3;
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + "....E....." + var3;
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + var3;
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + var3;
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + "333............................333333333";
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + "333......................A333..333333333";
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + "333....................A33333..333333333";
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + "333....aa............A3333333..333333333";
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + "333................A333333333..333333333";
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + "333...............33333333333..333333333";
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + "333....33.......3333333333333..333333333";
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + "333...........333333333333333...........";
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + "333...........333333333333333.........d.";
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + "..............333333333333333...........";
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + "33333..aa.....333333333333333...........";
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + "33333..33....." + var3;
      } else if (var1 == 3) {
         this.ggc.back_color(0, 255, 255);
         this.ggs_map_nst[0] = var2;
         this.ggs_map_nst[1] = var2;
         this.ggs_map_nst[2] = var2;
         this.ggs_map_nst[3] = var2;
         this.ggs_map_nst[4] = var2;
         this.ggs_map_nst[5] = var2;
         this.ggs_map_nst[6] = var2;
         this.ggs_map_nst[7] = var2;
         this.ggs_map_nst[8] = var2;
         this.ggs_map_nst[9] = var2;
         this.ggs_map_nst[10] = var2;
         this.ggs_map_nst[11] = ".................aaa..bc................";
         this.ggs_map_nst[12] = ".................aaa....................";
         this.ggs_map_nst[13] = var2;
         this.ggs_map_nst[14] = ".........................bc....bc..aa..5";
         this.ggs_map_nst[15] = "................56667...................";
         this.ggs_map_nst[16] = ".................fff......aa............";
         this.ggs_map_nst[17] = ".bc....bc....bc..fff.............566667.";
         this.ggs_map_nst[18] = ".................fff......E.......ffff..";
         this.ggs_map_nst[19] = ".......C.........fff....566667....ffff..";
         this.ggs_map_nst[20] = "......56667.....Cfff.....ffff.....ffff..";
         this.ggs_map_nst[21] = ".Z.....fff.....5666667...ffff.....ffff..";
         this.ggs_map_nst[22] = "1111...fff......fffff....ffff.....ffff..";
         this.ggs_map_nst[23] = "1111...fff......fffff....ffff.....ffff..";
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + var2;
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + var2;
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + var2;
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + "....................bc.......bc........b";
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + var2;
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + var2;
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + "..............bc.....567......G.........";
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + "......................f.................";
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + "......................f.................";
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + "..bc..................f.................";
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + "......................f.................";
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + "a.a.a..I..........H...f...............bc";
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + "...............bc.....f.................";
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + "B.....................f.................";
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + "666667................f........bc.......";
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + "fffff......aa.........f.................";
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + "fffff.....5667........f.................";
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + "fffff......ff.........f.................";
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + "fffff..aaaaff.........f.................";
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + "fffff..aaaaffE........f.................";
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + "fffff.56666667........f.................";
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + "fffff..ffffff.........f.................";
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + "fffff..ffffff.........f.................";
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + "fffff..ffffff.........f.................";
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + var2;
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + var2;
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + var2;
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + "c.....bc.....bc.......bc................";
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + "...........I........a...................";
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + "....................a...................";
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + "......567.....567...a...................";
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + ".......f.......f....a...................";
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + ".......f.......f....a...................";
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + ".......f.......f....a.......bc.......bc.";
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + ".......f.......f........................";
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + ".......f.......f....a................I..";
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + ".......f.......f..................C.....";
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + ".......f.......f....a....bc.aa...567....";
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + "...bc..f.......f............aa....f.....";
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + ".......f.......f....a...I.........f.....";
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + ".......f.......f..................f.....";
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + ".......f.......f...567.....5667...f.....";
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + ".......f.......f....f.......ff....f.....";
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + ".......f.......f....f.......ff...Pf.....";
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + ".......f.......f....f.......ff...567....";
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + ".......f.......f....f.......ff....f.....";
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + ".......f.......f....f.......ff....f.....";
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + ".......f.......f....f.......ff....f.....";
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + var2;
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + var2;
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + var2;
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + ".......................bc...............";
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + var2;
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + "..........................D.............";
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + "..........................1.............";
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + "........................D11.............";
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + "..........bc............111.............";
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + "..............I...I....1111...d.........";
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + ".aa..................D11111.............";
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + ".........B...........111111.............";
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + ".C......1111111111111111111111111111111.";
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + "5667....1111111111111111111111111111111.";
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + ".ff.....1111111111111111111111111111111.";
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + ".ff.....1111111111111111111111111111111.";
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + ".ff.....1111111.........................";
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + ".ff.111.1111111.111111111.aaaaaaa.111111";
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + ".ff........1111.111111111.aaaaaaa.111111";
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + ".ff.111111.1111.111111111.aaaaaaa.111111";
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + ".ff.111111..111.111111111e11111111111111";
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + ".ff.1111111.111.111111111111111111111111";
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + ".ff.1111111.....111111111111111111111111";
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + ".ff.111111111111111111111111111111111111";
      } else if (var1 == 4) {
         this.ggc.back_color(0, 255, 255);
         this.ggs_map_nst[0] = var2;
         this.ggs_map_nst[1] = var2;
         this.ggs_map_nst[2] = var2;
         this.ggs_map_nst[3] = var2;
         this.ggs_map_nst[4] = var2;
         this.ggs_map_nst[5] = var2;
         this.ggs_map_nst[6] = var2;
         this.ggs_map_nst[7] = var2;
         this.ggs_map_nst[8] = var2;
         this.ggs_map_nst[9] = var2;
         this.ggs_map_nst[10] = var2;
         this.ggs_map_nst[11] = var2;
         this.ggs_map_nst[12] = var2;
         this.ggs_map_nst[13] = var2;
         this.ggs_map_nst[14] = var2;
         this.ggs_map_nst[15] = var2;
         this.ggs_map_nst[16] = ".....................bc.....bc......bc..";
         this.ggs_map_nst[17] = var2;
         this.ggs_map_nst[18] = "..bc....bc..............................";
         this.ggs_map_nst[19] = "...............J..P.......K.K.JP.......L";
         this.ggs_map_nst[20] = ".............1111111..11111111111..11111";
         this.ggs_map_nst[21] = "................aaa1..11111111111..11111";
         this.ggs_map_nst[22] = ".Z........J....Kaaa1..11111111111..11111";
         this.ggs_map_nst[23] = "22222..2222222222222..11111111111..11111";
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + var2;
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + var2;
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + var2;
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + var2;
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + var2;
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + var2;
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + var2;
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + var2;
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + var2;
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + var2;
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + var2;
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + var2;
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + var2;
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + var2;
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + var2;
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + var2;
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + "......bc................................";
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + "................................bc......";
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + ".....a.....a............................";
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + ".P...a.....a........bc...............L..";
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + "111..a.....a.....................11..111";
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + "111..1.....1..P............1............";
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + "111..1....K1..11.......L...1.L..........";
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + "111..1111111..11..2222222222222222222222";
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + var2;
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + var2;
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + var2;
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + var2;
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + var2;
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + var2;
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + var2;
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + var2;
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + var2;
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + var2;
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + var2;
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + var2;
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + var2;
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + var2;
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + "........bc....bc......bc..........bc....";
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + "..................I........I..aaaa...a..";
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + ".....................................a..";
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + ".bc.........J..K.........O........O..a..";
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + "........J..111111...111111...111111..a..";
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + "P.......111111111...111111...111111..a..";
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + "111..111111111111...111111...111111..a..";
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + ".....111111111111...111111...111111..a..";
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + ".....111111111111...111111...111111.....";
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + "222..111111111111...111111...111111..2..";
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + var2;
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + var2;
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + var2;
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + var2;
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + var2;
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + var2;
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + var2;
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + var2;
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + var2;
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + var2;
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + var2;
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + var2;
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + var2;
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + var2;
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + ".bc" + var2;
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + var2;
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + var2;
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + ".....bc.....bc....bc....bc..............";
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + var2;
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + var2;
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + "..aaaaaaa" + var2;
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + "...........................M............";
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + "........O....222222222222222222222222222";
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + "..2222222..22222222222222222222222222222";
      } else if (var1 == 5) {
         this.ggc.back_color(1, 1, 1);
         this.ggs_map_nst[0] = var3;
         this.ggs_map_nst[1] = "33................33333333............D ";
         this.ggs_map_nst[2] = "33.........333333.33333333.333333aaaaa33";
         this.ggs_map_nst[3] = "33.......33333333........D....333aaaaa33";
         this.ggs_map_nst[4] = "33...C...333333333333333333333333aaaaa33";
         this.ggs_map_nst[5] = "33..333..3333333333333333333333333333333";
         this.ggs_map_nst[6] = "33.......3333333333333333333333333333333";
         this.ggs_map_nst[7] = "33.......3333333333333333333333333333333";
         this.ggs_map_nst[8] = "33.......3333333333333333333333333333333";
         this.ggs_map_nst[9] = "33..333..3333333333333333333333333333333";
         this.ggs_map_nst[10] = "33..aaa..3333333333333333333333333333333";
         this.ggs_map_nst[11] = "33.......333333333333333333333333333333.";
         this.ggs_map_nst[12] = "33.......333333333333333333333333333333.";
         this.ggs_map_nst[13] = "33..333..333333333333333333333333333333.";
         this.ggs_map_nst[14] = "33.......333333333333333333333333333333.";
         this.ggs_map_nst[15] = "33.......333333333333333333333333333333.";
         this.ggs_map_nst[16] = "33.......333333333333333333333333333333.";
         this.ggs_map_nst[17] = "33..333..333333333333333333333333333333.";
         this.ggs_map_nst[18] = "33.......333333333333333333333333333333.";
         this.ggs_map_nst[19] = "33.......333333333333333333333333333333.";
         this.ggs_map_nst[20] = "33...Z...333333333333333333333333333333.";
         this.ggs_map_nst[21] = "33..333..333333333333333333333333333333.";
         this.ggs_map_nst[22] = "33ee333ee3333333333333333333333333333333";
         this.ggs_map_nst[23] = var3;
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + var3;
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + "." + var3;
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + "." + var3;
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + ".33aaaa" + var3;
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + ".33aaaa" + var3;
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + ".33aaaa" + var3;
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + "........E." + var3;
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + "333333333a" + var3;
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + "333333333a" + var3;
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + "333333333a" + var3;
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + "333333333a" + var3;
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + ".........." + var3;
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + "..........333333333333333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + "..........333333333333333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + "..3.....3.333333333333333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + "ee3ee3ee3e333333333333333333333333333333";
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + "333333333333333333......................";
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + "333333333333333333......................";
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + "333333333333333333....j....j....j....j..";
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + "aaaaaa..................................";
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + "........................................";
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + ".........C.....C..........G.............";
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + "333333ee3333ee3333ee3eeeeeeeeeeeeeeeeeee";
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + var3;
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + var3;
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + var3;
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + var3;
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + var3;
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + var3;
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + var3;
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + var3;
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + var3;
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + var3;
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + var3;
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + var3;
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + var3;
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + "..........33333333333333333..33333333333";
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + "..........33333333333333333..33333333333";
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + ".....3333.33333333333333333..33333333333";
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + "....E...3...............E..............3";
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + "....3333333333333333333333333333.......3";
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + "....E..3333333333333333333333333...d...3";
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + "...33333333333333333333333333333.......3";
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + "....E.33333333333333333333333333.......3";
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + "..333333333333333333333333333333..e....3";
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + ".." + var3;
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + "e" + var3;
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + var3;
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + var3;
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + var3;
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + var3;
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + var3;
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + var3;
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + var3;
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + var3;
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + var3;
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + var3;
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + var3;
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + var3;
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + var3;
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + var3;
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + var3;
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + var3;
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + var3;
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + var3;
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + var3;
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + var3;
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + var3;
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + var3;
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + var3;
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + var3;
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + var3;
      } else if (var1 == 6) {
         this.ggc.back_color(0, 255, 255);
         this.ggs_map_nst[0] = var2;
         this.ggs_map_nst[1] = var2;
         this.ggs_map_nst[2] = var2;
         this.ggs_map_nst[3] = var2;
         this.ggs_map_nst[4] = var2;
         this.ggs_map_nst[5] = var2;
         this.ggs_map_nst[6] = "........................................";
         this.ggs_map_nst[7] = "......................bc.....bc.........";
         this.ggs_map_nst[8] = ".............aaa........................";
         this.ggs_map_nst[9] = ".............aaa........................";
         this.ggs_map_nst[10] = ".............aaa.567......F.......I.....";
         this.ggs_map_nst[11] = "..................f....................1";
         this.ggs_map_nst[12] = "..........bc......f...................11";
         this.ggs_map_nst[13] = "..................f..................111";
         this.ggs_map_nst[14] = "..................f.....................";
         this.ggs_map_nst[15] = "..............H...f.......bc............";
         this.ggs_map_nst[16] = "..................f.....................";
         this.ggs_map_nst[17] = "....bc............f.....................";
         this.ggs_map_nst[18] = "..........a.......f.....................";
         this.ggs_map_nst[19] = "..................f.....................";
         this.ggs_map_nst[20] = "..................f...........bc........";
         this.ggs_map_nst[21] = ".Z....I..567......f.....................";
         this.ggs_map_nst[22] = "111.......f.......f.....................";
         this.ggs_map_nst[23] = "111.......f.......f.....................";
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + ".........................111111111111111";
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + ".........................1aaaaaa1.......";
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + ".........................1aaaaaa11111111";
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + ".........................1aaaaaa1.......";
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + ".........................111111.1.111111";
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + ".....bc..................11.....1......1";
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + ".........................1..1111111111.1";
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + ".........................1.11..........1";
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + "..1....I.................1....11111.1111";
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + ".11......................111111.......11";
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + "111......................1....1.11111..1";
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + "111..........bc..........1.111111...11.1";
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + "111......................1....1...1....1";
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + "111......................1111.1.11111111";
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + ".........................1....1........1";
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + "........eeeeeeee.........1.11111111111.1";
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + "........11111111.........1.............1";
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + ".........................11111.11111.111";
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + ".........................1.....1.......1";
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + "...........1aaaa.........1.1111111111111";
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + "......H....1aaaa...L.....1..............";
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + "...........1aaaa..567....11111111.111111";
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + "...........11111...f....L...............";
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + "...........11111...f....1111111111111111";
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + "1111111111111111111111111111111.........";
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + "...1...1...1...1111...1..aaaaa1111111111";
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + "11...1...1...1......1...1aaaaa1........1";
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + ".111111111111111.11111111111111.111111.1";
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + "..............................1........1";
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + "11111111111.1111111111.111111.11111.1111";
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + "....1...........1......1...............1";
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + "111.1.111111111.11.11111.1111111111111.1";
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + "....1...1.....1..1...111...............1";
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + ".111111.1.111.11.111..111111111111111.11";
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + "....1...1...1......11..............1...1";
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + "111.1.11111.111111.11111111.111111.1.1.1";
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + "....1...........1..1........1......1...1";
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + ".111111111111.1.1.11.11111111.1111111111";
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + "....1.........1...1..1..........1......1";
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + "111.1.1111111111111.11.1111111111.111111";
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + "....1.....1.........1.........1...1.....";
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + ".11111111.1.111111111.1111111.1.111.1111";
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + "....1.....1....1......1.......1...1....1";
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + "111.1.11111111.1.111111.111111111.1111.1";
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + "....1.....1....1......1.......1...1....1";
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + "111111111.1.111111111.111111111.111.1111";
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + "....1.....1....1........................";
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + "111.111111111111111111111111111111111111";
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + var2;
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + var2;
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + var2;
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + var2;
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + var2;
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + "e.................1.....................";
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + "11111111..........1.....................";
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + ".aaaaaa1..........1.....................";
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + ".aaaaaa11111......1.....................";
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + ".1111111...1.111111.....................";
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + "....1....1........1.....................";
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + "111.1.111111111111111111111111111111111.";
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + "....1..........................1........";
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + ".111111111111111.1111111111111.1........";
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + ".................1.........1...1........";
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + "111111.11111111111.1111.1111.111........";
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + ".................1....1....1...1........";
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + "11.111111111111111111.1111.111.1........";
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + "...1..................1....1...1........";
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + ".1111111111111111111111.1111.111........";
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + "......................1....1...1d.......";
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + "11111111111111111111111111.1111111111111";
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + ".......................................1";
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + "1111111111111111111111111111111111111111";
      } else if (var1 == 7) {
         this.ggc.back_color(1, 1, 1);
         this.ggs_map_nst[0] = var4;
         this.ggs_map_nst[1] = var4;
         this.ggs_map_nst[2] = var4;
         this.ggs_map_nst[3] = var4;
         this.ggs_map_nst[4] = var4;
         this.ggs_map_nst[5] = var4;
         this.ggs_map_nst[6] = var4;
         this.ggs_map_nst[7] = var4;
         this.ggs_map_nst[8] = var4;
         this.ggs_map_nst[9] = var4;
         this.ggs_map_nst[10] = var4;
         this.ggs_map_nst[11] = var4;
         this.ggs_map_nst[12] = var4;
         this.ggs_map_nst[13] = var4;
         this.ggs_map_nst[14] = var4;
         this.ggs_map_nst[15] = var4;
         this.ggs_map_nst[16] = "44444444444444..........................";
         this.ggs_map_nst[17] = "44444444444444..........................";
         this.ggs_map_nst[18] = "........444444..........................";
         this.ggs_map_nst[19] = ".Z........4444..................h....h..";
         this.ggs_map_nst[20] = "444444..................a.......i....i..";
         this.ggs_map_nst[21] = "44444444.............................O..";
         this.ggs_map_nst[22] = "4444444444444444...44..484..4...444444..";
         this.ggs_map_nst[23] = "4444444444444444gNg44gg444gg4gNg444444gN";
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + var4;
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + var4;
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + var4;
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + var4;
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + var4;
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + var4;
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + var4;
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + var4;
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + var4;
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + var4;
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + var4;
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + var4;
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + var4;
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + var4;
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + var4;
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + "4444444444444444444444494444444444444449";
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + var2;
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + var2;
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + "....aa..................................";
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + "..h.aa.....44448444444444444444844444444";
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + "..i.aa...4444444444444444444444444444444";
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + "......O444444444444444444444444444444444";
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + ".444444444444444444444444444444444444444";
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + "g444444444444444444444444444444444444444";
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + var4;
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + var4;
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + var4;
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + var4;
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + var4;
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + var4;
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + var4;
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + var4;
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + var4;
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + var4;
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + var4;
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + var4;
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + var4;
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + var4;
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + var4;
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + var4;
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + "..............44444444444444444444444444";
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + "................444444444444444444444444";
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + var2;
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + "444444484444.........h........h........h";
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + "44444444444444.......i........i........i";
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + "4444444444444444........................";
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + "444444444444444444......O........O......";
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + "4444444444444444444444444gNg444444gNg444";
         var10000 = this.ggs_map_nst;
         var10000[0] = var10000[0] + var4;
         var10000 = this.ggs_map_nst;
         var10000[1] = var10000[1] + var4;
         var10000 = this.ggs_map_nst;
         var10000[2] = var10000[2] + var4;
         var10000 = this.ggs_map_nst;
         var10000[3] = var10000[3] + var4;
         var10000 = this.ggs_map_nst;
         var10000[4] = var10000[4] + var4;
         var10000 = this.ggs_map_nst;
         var10000[5] = var10000[5] + var4;
         var10000 = this.ggs_map_nst;
         var10000[6] = var10000[6] + var4;
         var10000 = this.ggs_map_nst;
         var10000[7] = var10000[7] + var4;
         var10000 = this.ggs_map_nst;
         var10000[8] = var10000[8] + var4;
         var10000 = this.ggs_map_nst;
         var10000[9] = var10000[9] + var4;
         var10000 = this.ggs_map_nst;
         var10000[10] = var10000[10] + var4;
         var10000 = this.ggs_map_nst;
         var10000[11] = var10000[11] + var4;
         var10000 = this.ggs_map_nst;
         var10000[12] = var10000[12] + var4;
         var10000 = this.ggs_map_nst;
         var10000[13] = var10000[13] + var4;
         var10000 = this.ggs_map_nst;
         var10000[14] = var10000[14] + var4;
         var10000 = this.ggs_map_nst;
         var10000[15] = var10000[15] + var4;
         var10000 = this.ggs_map_nst;
         var10000[16] = var10000[16] + var4;
         var10000 = this.ggs_map_nst;
         var10000[17] = var10000[17] + "444444" + var2;
         var10000 = this.ggs_map_nst;
         var10000[18] = var10000[18] + var2;
         var10000 = this.ggs_map_nst;
         var10000[19] = var10000[19] + ".aaaa.........h....h....h...............";
         var10000 = this.ggs_map_nst;
         var10000[20] = var10000[20] + ".aaaa.........i....i....i...............";
         var10000 = this.ggs_map_nst;
         var10000[21] = var10000[21] + ".aaaa......................M......Y.....";
         var10000 = this.ggs_map_nst;
         var10000[22] = var10000[22] + "............." + var4;
         var10000 = this.ggs_map_nst;
         var10000[23] = var10000[23] + "444444ggNgg" + var4;
      }

      var6 = 0;

      do {
         var7 = 0;

         do {
            char var8 = this.ggs_map_nst[var6].charAt(var7);
            byte var9;
            if (var8 == 'a') {
               var9 = 80;
            } else if (var8 == 'b') {
               var9 = 81;
            } else if (var8 == 'c') {
               var9 = 82;
            } else if (var8 == 'd') {
               var9 = 83;
            } else if (var8 == 'e') {
               var9 = 84;
            } else if (var8 == 'f') {
               var9 = 85;
            } else if (var8 == 'g') {
               var9 = 86;
            } else if (var8 == 'h') {
               var9 = 87;
            } else if (var8 == 'i') {
               var9 = 88;
            } else if (var8 == 'j') {
               var9 = 89;
            } else if (var8 == '1') {
               var9 = 90;
            } else if (var8 == '2') {
               var9 = 91;
            } else if (var8 == '3') {
               var9 = 92;
            } else if (var8 == '4') {
               var9 = 93;
            } else if (var8 == '5') {
               var9 = 94;
            } else if (var8 == '6') {
               var9 = 95;
            } else if (var8 == '7') {
               var9 = 96;
            } else if (var8 == '8') {
               var9 = 97;
               this.t_set(450, var7 * 32, var6 * 32);
            } else if (var8 == '9') {
               var9 = 97;
               this.t_set(460, var7 * 32, var6 * 32);
            } else if (var8 == 'A') {
               var9 = 0;
               this.t_set(100, var7 * 32, var6 * 32);
            } else if (var8 == 'B') {
               var9 = 0;
               this.t_set(100, var7 * 32, var6 * 32);
               this.t_set(101, var7 * 32 + 55, var6 * 32);
               this.t_set(102, var7 * 32 + 110, var6 * 32);
            } else if (var8 == 'C') {
               var9 = 0;
               this.t_set(120, var7 * 32, var6 * 32);
            } else if (var8 == 'D') {
               var9 = 0;
               this.t_set(200, var7 * 32, var6 * 32);
            } else if (var8 == 'E') {
               var9 = 0;
               this.t_set(220, var7 * 32, var6 * 32);
            } else if (var8 == 'F') {
               var9 = 0;
               this.t_set(500, var7 * 32, var6 * 32);
            } else if (var8 == 'G') {
               var9 = 0;
               this.t_set(500, var7 * 32, var6 * 32);
               this.t_set(501, var7 * 32, var6 * 32);
            } else if (var8 == 'H') {
               var9 = 0;
               this.t_set(510, var7 * 32, var6 * 32);
            } else if (var8 == 'I') {
               var9 = 0;
               this.t_set(300, var7 * 32, var6 * 32);
            } else if (var8 == 'J') {
               var9 = 0;
               this.t_set(350, var7 * 32, var6 * 32);
            } else if (var8 == 'K') {
               var9 = 0;
               this.t_set(360, var7 * 32, var6 * 32);
            } else if (var8 == 'L') {
               var9 = 0;
               this.t_set(370, var7 * 32, var6 * 32);
            } else if (var8 == 'M') {
               var9 = 0;
               this.cc_boss.c = 100;
               this.cc_boss.x = var7 * 32 - 16;
               this.cc_boss.y = var6 * 32 - 16;
               this.cc_boss.c1 = 0;
               this.cc_boss.tai = 3;
            } else if (var8 == 'N') {
               var9 = 86;
               this.t_set(470, var7 * 32, var6 * 32 + 32);
            } else if (var8 == 'O') {
               var9 = 0;
               this.t_set(140, var7 * 32, var6 * 32);
            } else if (var8 == 'P') {
               var9 = 0;
               this.t_set(380, var7 * 32, var6 * 32);
            } else if (var8 == 'Y') {
               var9 = 26;
            } else if (var8 == 'Z') {
               var9 = 0;
               this.cc_j.x = var7 * 32;
               this.cc_j.y = var6 * 32;
            } else {
               var9 = 0;
            }

            this.ggs_map_data[var6 * 160 + var7] = var9;
            ++var7;
         } while(var7 <= 159);

         ++var6;
      } while(var6 <= 23);

      this.ggc.map_data_set(this.ggs_map_data);
      this.ggc.map_bg_set();
   }

   public boolean action(Event var1, Object var2) {
      if (var1.target == this.again) {
         if (this.g_mode == 210) {
            this.g_mode_c = 30;
            this.g_mode = 50;
            this.again.hide();
            this.entryName.hide();
            this.send.hide();
            return true;
         }
      } else if (var1.target == this.send) {
         this.send.hide();
         this.entryName.hide();
         this.again.hide();
         this.g_mode_c = 30;
         this.g_mode = 50;
         String var3 = "Super_M";
         String var4 = this.entryName.getText().replace('&', '_');
         this.cgi.call("course=" + var3 + "&name=" + var4 + "&time=" + this.score);
         return true;
      }

      return false;
   }

   public void run() {
      while(true) {
         try {
            Thread.sleep((long)this.interval1);
         } catch (InterruptedException var1) {
         }

         if (this.g_mode == 100) {
            this.g_main();
         } else {
            this.g_main2();
         }

         this.repaint();
      }
   }

   public void init() {
      this.resize(384, 256);
      
      this.ap_width = this.size().width;
      this.ap_height = this.size().height;
      this.setForeground(Color.black);
      this.setBackground(Color.white);
      this.ggs_init();
      this.ggc.back_color(1, 1, 1);
      this.cc_j = new c_cc();
      int var1 = 0;

      do {
         this.cc_t[var1] = new c_cc();
         ++var1;
      } while(var1 <= 63);

      this.again = new Button("Play Again");
      this.again.reshape(150, 150, 100, 30);
      this.add(this.again);
      this.send = new Button("Send Record");
      this.send.reshape(150, 190, 100, 30);
      this.add(this.send);
      this.entryName = new TextField(10);
      this.entryName.reshape(270, 190, 100, 30);
      this.add(this.entryName);
      this.again.hide();
      this.send.hide();
      this.entryName.hide();
      this.param = this.getParameter("Scorepath");
      if (this.param != null) {
         this.m_cfgfile = this.param;
      }
      
      String par = this.getParameter("Stage");
      if (par != null) {
    	  try {
    		  int i = Integer.parseInt(par);
    		  if (i > 1 && i < 8) {
    			  this.level = i;
    		  }
    	  } catch (NumberFormatException e) {}
      }
      
      par = this.getParameter("Lives");
      if (par != null) {
    	  try {
    		  int i = Integer.parseInt(par);
    		  if (i >= 1) {
    			  this.lives = i;
    		  }
    	  } catch (NumberFormatException e) {}
      }
      
      if (this.m_cfgfile != "") {
         this.readcfgFile();
      }

      if (this.URLName == null) {
         this.URLName = "http://www.FreeArcade.com";
      }

      this.URLName = this.URLName + "/cgi-bin/superm_record.cgi";
      this.cgi = new CGIcall(this.URLName);
      this.cc_boss = new c_cc();
      this.cc_j.x = 100;
      this.cc_j.y = 24;
      this.score = 0;
      this.highscore = 0;
      this.tread1 = new Thread(this);
      this.tread1.start();
      
      setFocusable(true);
      requestFocusInWindow();
   }

   void g_main2() {
      this.ggc.back_fill();
      Font var1;
      Font var2;
      switch(this.g_mode) {
      case 0:
         this.g_mode = 50;
         break;
      case 50:
         this.ggs_g.setColor(Color.black);
         this.ggs_g.fillRect(0, 0, this.ap_width, this.ap_height);
         int var8 = 0;

         do {
            int var9 = 0;

            do {
               this.ggc.pt_draw(30 + var8 * 10 + var9, 96 + var9 * 32, 48 + var8 * 32, 0);
               ++var9;
            } while(var9 <= 5);

            ++var8;
         } while(var8 <= 3);

         this.ggs_g.setColor(Color.blue);
         var1 = new Font("Dialog", 1, 12);
         this.ggs_g.setFont(var1);
         this.ggs_g.drawString("By NaotoFukuda", 140, 200);
         this.ggs_g.drawString("Click to start", 150, 220);
         this.ggs_g.drawString("copyright 1999 Camadro Inc.", 110, 250);
         break;
      case 80:
         this.shokika_2();
         this.ggs_g.setColor(Color.black);
         this.ggs_g.fillRect(0, 0, this.ap_width, this.ap_height);
         this.ggs_g.setColor(Color.white);
         var2 = new Font("Dialog", 1, 20);
         this.ggs_g.setFont(var2);
         this.ggs_g.drawString("Stage " + Integer.toString(this.stage), 150, 125);
         this.g_mode = 90;
         break;
      case 90:
         this.shokika_3();
         this.ggs_g.setColor(Color.black);
         this.ggs_g.fillRect(0, 0, this.ap_width, this.ap_height);
         this.ggs_g.setColor(Color.white);
         Font var3 = new Font("Dialog", 1, 20);
         this.ggs_g.setFont(var3);
         if (this.stage == 7) {
            this.ggs_g.drawString("Last stage", 140, 125);
         } else {
            this.ggs_g.drawString("Stage " + Integer.toString(this.stage), 150, 125);
         }

         this.g_mode = 95;
         this.g_mode_c = 0;
         break;
      case 95:
         this.ggs_g.setColor(Color.black);
         this.ggs_g.fillRect(0, 0, this.ap_width, this.ap_height);
         this.ggs_g.setColor(Color.white);
         Font var4 = new Font("Dialog", 1, 20);
         this.ggs_g.setFont(var4);
         if (this.stage == 7) {
            this.ggs_g.drawString("Last stage", 140, 125);
         } else {
            this.ggs_g.drawString("Stage " + Integer.toString(this.stage), 150, 125);
         }

         ++this.g_mode_c;
         if (this.g_mode_c > 16) {
            this.g_mode = 100;
         }
         break;
      case 200:
         this.ggs_g.setColor(Color.black);
         this.ggs_g.fillRect(0, 0, this.ap_width, this.ap_height);
         this.ggs_g.setColor(Color.red);
         Font var5 = new Font("Dialog", 1, 20);
         this.ggs_g.setFont(var5);
         this.ggs_g.drawString("Game over", 140, 125);
         this.g_mode = 210;
         this.g_mode_c = 0;
         break;
      case 210:
         this.ggs_g.setColor(Color.black);
         this.ggs_g.fillRect(0, 0, this.ap_width, this.ap_height);
         this.ggs_g.setColor(Color.red);
         Font var6 = new Font("Dialog", 1, 20);
         this.ggs_g.setFont(var6);
         this.ggs_g.drawString("Game over", 140, 125);
         this.again.show();
         oldscore = (long)this.score;
         if (this.score >= 2000) {
            this.send.show();
            this.entryName.show();
            this.ggs_g.setColor(Color.green);
            var1 = new Font("TimesRoman", 1, 12);
            this.ggs_g.setFont(var1);
            this.ggs_g.drawString("Great Score!", 270, 150);
            this.ggs_g.drawString("Enter your name", 270, 170);
            if (this.entryName.getText().equals("")) {
               this.send.disable();
            } else {
               this.send.enable();
            }
         }

         if (this.g_mode_c > 30) {
            this.g_mode = 50;
         }
         break;
      case 300:
         this.ggs_g.setColor(Color.black);
         this.ggs_g.fillRect(0, 0, this.ap_width, this.ap_height);
         this.ggc.map_draw(this.map_wx, this.map_wy);
         this.ggc.pt_draw(1, 160, 160, 1);
         this.g_mode = 310;
         this.g_mode_c = 0;
         break;
      case 310:
         this.ggs_g.setColor(Color.black);
         this.ggs_g.fillRect(0, 0, this.ap_width, this.ap_height);
         this.ggc.map_draw(this.map_wx, this.map_wy);
         this.ggc.pt_draw(1, 160, 160, 1);
         ++this.g_mode_c;
         if (this.g_mode_c >= 5) {
            if (this.g_mode_c <= 37) {
               int var11 = 100 + (37 - this.g_mode_c);
               this.ggc.pt_draw(25, 184, var11, 0);
               if (this.g_mode_c == 37) {
                  this.score_add(1000);
               }
            } else {
               this.ggc.pt_draw(25, 184, 100, 0);
            }
         }

         if (this.g_mode_c > 50) {
            this.g_mode = 320;
            this.g_mode_c = 0;
            this.ggc.back_color(0, 255, 255);
            this.ggc.map_bg_set();
         }
         break;
      case 320:
         this.ggc.back_fill();
         this.ggc.map_draw(this.map_wx, this.map_wy);
         this.ggc.pt_draw(1, 160, 160, 1);
         this.ggc.pt_draw(25, 184, 100, 0);
         this.ggs_g.setColor(Color.red);
         Font var7 = new Font("Dialog", 1, 20);
         this.ggs_g.setFont(var7);
         this.ggs_g.drawString("Congratulations!", 124, 76);
         ++this.g_mode_c;
         if (this.g_mode_c > 60) {
            this.g_mode = 50;
         }
      }

      if (this.g_mode >= 50) {
         String var10 = "Score " + Integer.toString(this.score);
         var10 = var10 + "    HighScore " + Integer.toString(this.highscore);
         var10 = var10 + "    Left " + Integer.toString(this.j_left);
         this.ggs_g.setColor(Color.blue);
         var2 = new Font("Dialog", 1, 12);
         this.ggs_g.setFont(var2);
         this.ggs_g.drawString(var10, 32, 16);
      }

   }

   void t_set(int var1, int var2, int var3) {
      int var4 = 0;

      do {
         if (this.cc_t[var4].c == 0) {
            this.cc_t[var4].c = var1;
            this.cc_t[var4].x = var2;
            this.cc_t[var4].y = var3;
            this.cc_t[var4].af = false;
            this.cc_t[var4].c1 = 0;
            this.cc_t[var4].c6 = var2 - 400;
            ++this.t_kazu;
            switch(var1) {
            case 101:
               this.cc_t[var4].c = 100;
               this.cc_t[var4].c6 = var2 - 400 - 55;
               return;
            case 102:
               this.cc_t[var4].c = 100;
               this.cc_t[var4].c6 = var2 - 400 - 110;
               return;
            case 140:
               this.cc_t[var4].tai = 2;
               return;
            case 300:
               this.cc_t[var4].c1 = this.cc_t[var4].y - 30;
               this.cc_t[var4].c2 = this.cc_t[var4].y + 30;
               this.cc_t[var4].vy = -60;
               return;
            case 350:
               this.cc_t[var4].vy = 0;
               return;
            case 360:
               this.cc_t[var4].vy = 0;
               return;
            case 370:
               this.cc_t[var4].vy = 0;
               return;
            case 450:
               this.cc_t[var4].c1 = 90;
               this.cc_t[var4].vx = var2;
               this.cc_t[var4].vy = var3;
               return;
            case 460:
               this.cc_t[var4].c1 = 270;
               this.cc_t[var4].vx = var2;
               this.cc_t[var4].vy = var3;
               return;
            case 470:
               this.cc_t[var4].vy = 0;
               return;
            case 500:
               this.cc_t[var4].c1 = this.cc_t[var4].x - 64;
               this.cc_t[var4].c2 = this.cc_t[var4].x + 64;
               this.cc_t[var4].vx = 40;
               this.cc_t[var4].c6 = var2 - 500;
               return;
            case 501:
               this.cc_t[var4].c = 500;
               this.cc_t[var4].x = var2 + 272;
               this.cc_t[var4].c1 = this.cc_t[var4].x - 64;
               this.cc_t[var4].c2 = this.cc_t[var4].x + 64;
               this.cc_t[var4].vx = -40;
               this.cc_t[var4].vy = 0;
               this.cc_t[var4].c6 = var2 - 500;
               return;
            case 510:
               this.cc_t[var4].c1 = this.cc_t[var4].y - 100;
               this.cc_t[var4].c2 = this.cc_t[var4].y + 100;
               this.cc_t[var4].vx = 0;
               this.cc_t[var4].vy = -40;
               this.cc_t[var4].c6 = var2 - 500;
               return;
            default:
               return;
            }
         }

         ++var4;
      } while(var4 <= 28);

   }

   void boss_move() {
      int var1 = 0;
      int var2 = this.cc_boss.c;
      int var3 = this.cc_boss.x;
      int var4 = this.cc_boss.y;
      int var5 = this.cc_boss.c1;
      c_cc var10000;
      switch(var2) {
      case 50:
         var1 = 9;
         ++var5;
         if (var5 == 4) {
            var10000 = this.cc_boss;
            var10000.tai += -1;
            if (this.cc_boss.tai > 0) {
               var2 = 130;
            }
         }

         if (var5 >= 10) {
            var2 = 0;
         }
         break;
      case 55:
         var1 = 10;
         ++var5;
         if (var5 == 4) {
            var10000 = this.cc_boss;
            var10000.tai += -1;
            if (this.cc_boss.tai > 0) {
               var2 = 230;
            }
         }

         if (var5 >= 10) {
            var2 = 0;
         }
         break;
      case 100:
         if (this.stage == 7) {
            var1 = 1;
         } else {
            var1 = 5;
         }
         break;
      case 110:
         if (this.stage == 7) {
            var1 = 1;
         } else {
            var1 = 5;
         }

         ++var5;
         if (var5 == 2) {
            this.t_set_m(420, var3, var4 - 12, 0);
         }

         if (var5 >= 20) {
            if (this.stage == 7) {
               var1 = 1 + this.c_1 * 2;
            } else {
               var1 = 5 + this.c_1 * 2;
            }

            var5 = 40;
            var3 -= 3;
            if (var3 <= 4640) {
               var3 = 4640;
               var2 = 120;
               var5 = 0;
            }
         }
         break;
      case 120:
         if (this.stage == 7) {
            var1 = 1;
         } else {
            var1 = 5;
         }

         ++var5;
         if (var5 == 2 || var5 == 25) {
            this.t_set_m(420, var3, var4 - 12, 0);
         }

         if (var5 == 45 || var5 == 60 || var5 == 82 || var5 == 95) {
            this.t_set_m(430, var3 - 8, var4 + 16, 0);
         }

         if (var5 >= 115) {
            if (this.stage == 7) {
               var1 = 1 + this.c_1 * 2;
            } else {
               var1 = 5 + this.c_1 * 2;
            }

            var5 = 115;
            var3 += 3;
            if (var3 >= 4688) {
               var3 = 4688;
               var2 = 110;
               var5 = 0;
            }
         }
         break;
      case 130:
         if (this.stage == 7) {
            var1 = 1 + this.c_1 * 2;
         } else {
            var1 = 5 + this.c_1 * 2;
         }

         var3 -= 20;
         if (var3 <= 4368) {
            var3 = 4368;
            var2 = 210;
            var5 = 0;
         }
         break;
      case 210:
         if (this.stage == 7) {
            var1 = 2;
         } else {
            var1 = 6;
         }

         ++var5;
         if (var5 == 2) {
            this.t_set_m(420, var3, var4 - 12, 0);
         }

         if (var5 >= 20) {
            if (this.stage == 7) {
               var1 = 2 + this.c_1 * 2;
            } else {
               var1 = 6 + this.c_1 * 2;
            }

            var5 = 40;
            var3 += 3;
            if (var3 >= 4432) {
               var3 = 4432;
               var2 = 220;
               var5 = 0;
            }
         }
         break;
      case 220:
         if (this.stage == 7) {
            var1 = 2;
         } else {
            var1 = 6;
         }

         ++var5;
         if (var5 == 2 || var5 == 25) {
            this.t_set_m(420, var3, var4 - 12, 0);
         }

         if (var5 == 45 || var5 == 60 || var5 == 82 || var5 == 95) {
            this.t_set_m(440, var3 + 8, var4 + 16, 1);
         }

         if (var5 >= 115) {
            if (this.stage == 7) {
               var1 = 2 + this.c_1 * 2;
            } else {
               var1 = 6 + this.c_1 * 2;
            }

            var5 = 115;
            var3 -= 3;
            if (var3 <= 4368) {
               var3 = 4368;
               var2 = 210;
               var5 = 0;
            }
         }
         break;
      case 230:
         if (this.stage == 7) {
            var1 = 2 + this.c_1 * 2;
         } else {
            var1 = 6 + this.c_1 * 2;
         }

         var3 += 20;
         if (var3 >= 4688) {
            var3 = 4688;
            var2 = 110;
            var5 = 0;
         }
      }

      if (var2 == 0) {
         this.cc_boss.c = 0;
      } else {
         if (var2 >= 110 && this.cc_j.c == 100) {
            int var6 = Math.abs(this.cc_j.x - var3);
            int var7 = Math.abs(this.cc_j.y - var4);
            if (var6 < 36 && var7 < 32) {
               if (this.cc_j.vy > 0) {
                  if (var2 < 200) {
                     var2 = 50;
                  } else {
                     var2 = 55;
                  }

                  var5 = 0;
                  this.cc_j.y = var4 - 32;
                  this.cc_j.vy = -240;
               } else {
                  this.cc_j.c = 200;
               }
            }
         }

         this.cc_boss.c = var2;
         this.cc_boss.x = var3;
         this.cc_boss.y = var4;
         this.cc_boss.c1 = var5;
         this.cc_boss.pt = var1;
      }
   }

   private void readcfgFile() {
      try {
         URL var2 = new URL(this.getCodeBase(), this.m_cfgfile);

         try {
            DataInputStream var3 = new DataInputStream(var2.openStream());
            if (var3 != null) {
               String var1 = var3.readLine();
               if (var1 != null) {
                  this.URLName = var1;
                  return;
               }
            }
         } catch (IOException var4) {
            return;
         }
      } catch (MalformedURLException var5) {
         System.err.println("Could not open config file.");
      }

   }
}
