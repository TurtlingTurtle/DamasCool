import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Tablero extends JPanel{
	
	private Bloque[][] tablero;
	private int turno =1;
	private Panel panel;
	
	public Tablero(Panel panel){
		super();
		this.panel=panel;
		this.setPreferredSize(new Dimension(696,696));
		this.setLayout(null);
		this.tablero = new Bloque[8][4];
		for(int y=0;y<4;y++){
			for(int x=0;x<8;x++){
				if(y==0){
					tablero[x][y]=new Bloque(-1,x,y);	
				}
				else if(y==1 && x%2==1){
					tablero[x][y]=new Bloque(-1,x,y);
				}
				else if(y==3){
					tablero[x][y]=new Bloque(1,x,y);
				}
				else if(y==2 && x%2==0){
					tablero[x][y]=new Bloque(1,x,y);
				}
				else{
					tablero[x][y]=new Bloque(0,x,y);
				}
				Bloque t=tablero[x][y];
				this.add(t);
				t.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub	
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						if(((Bloque) e.getSource()).getFicha()>=turno && turno==1){
							turno=-1;
							panel.setColor2();
							((Bloque) e.getSource()).setFicha(2);
							System.out.println(turno);
							int[] temp=checarBloque((Bloque) e.getSource());
							System.out.println("UpLeft"+temp[0]);
							System.out.println("UpRight"+temp[1]);
							System.out.println("DownLeft"+temp[2]);
							System.out.println("DownRight"+temp[3]);
						}
						else if(((Bloque) e.getSource()).getFicha()<=turno && turno==-1){
							turno=1;
							panel.setColor1();
							((Bloque) e.getSource()).setFicha(-2);
							System.out.println(turno);
							int[] temp=checarBloque((Bloque) e.getSource());
							System.out.println("UpLeft"+temp[0]);
							System.out.println("UpRight"+temp[1]);
							System.out.println("DownLeft"+temp[2]);
							System.out.println("DownRight"+temp[3]);
						}
					}
				});
				if(x%2==0){
					t.setBounds(x*87,y*174+87,87,87);
				}
				else{
					t.setBounds(x*87,y*174,87,87);
				}
				
			}
		}
	}
	public int[] checarBloque(Bloque bloque){
		int x = bloque.getXB();
		int y = bloque.getYB();
		int[] temp = new int[4];
		if(x>0 && x<7){
			//checa todos los de en medio
			if(y>0 && y<3){
				if((x%2)==1){
					temp[0]=tablero[x-1][y-1].getFicha();
					temp[1]=tablero[x+1][y-1].getFicha();
					temp[2]=tablero[x-1][y].getFicha();
					temp[3]=tablero[x+1][y].getFicha();
				}
				else{
					temp[0]=tablero[x-1][y].getFicha();
					temp[1]=tablero[x+1][y].getFicha();
					temp[2]=tablero[x-1][y+1].getFicha();
					temp[3]=tablero[x+1][y+1].getFicha();
				}
			}
		}
		//checa si la fila es la fila 0
		else if(y==0){
			if((x%2)==0 && x!=0){
				temp[0]=tablero[x-1][y].getFicha();
				temp[1]=tablero[x+1][y].getFicha();
				temp[2]=tablero[x-1][y+1].getFicha();
				temp[3]=tablero[x+1][y+1].getFicha();
			}
			else if(x==0){
				temp[1]=tablero[x+1][y].getFicha();
				temp[3]=tablero[x+1][y+1].getFicha();
			}
			else if((x%2)==1 && x!=7){
				temp[2]=tablero[x-1][y].getFicha();
				temp[3]=tablero[x+1][y].getFicha();
			}
			else{
				temp[2]=tablero[x-1][y].getFicha();
			}
		}
		else if(y>0 && y<3){
			if((x%2)==0 && x!=0){
				temp[0]=tablero[x-1][y].getFicha();
				temp[1]=tablero[x+1][y].getFicha();
				temp[2]=tablero[x-1][y+1].getFicha();
				temp[3]=tablero[x+1][y+1].getFicha();
			}
			else if(x==0){
				temp[1]=tablero[x+1][y].getFicha();
				temp[3]=tablero[x+1][y+1].getFicha();
			}
			else if((x%2)==1 && x!=7){
				temp[2]=tablero[x-1][y].getFicha();
				temp[3]=tablero[x+1][y].getFicha();
			}
			else{
				temp[0]=tablero[x-1][y-1].getFicha();
				temp[2]=tablero[x-1][y].getFicha();
			}
		}
		//checa si la fila es la fila 3
		else if(y==3){
			if((x%2)==1 && x!=7){
				temp[0]=tablero[x-1][y-1].getFicha();
				temp[1]=tablero[x+1][y-1].getFicha();
				temp[2]=tablero[x-1][y].getFicha();
				temp[3]=tablero[x+1][y].getFicha();
			}
			else if(x==7){
				temp[0]=tablero[x-1][y-1].getFicha();
				temp[2]=tablero[x-1][y].getFicha();
			}
			else if((x%2)==0 && x!=0){
				temp[0]= tablero[x-1][y].getFicha();
				temp[1]= tablero[x-1][y].getFicha();
			}
			else if(x==0){
				temp[1]=tablero[x+1][y].getFicha();
			}
		}
		return temp;
	}
	public void moverse(Bloque bloque){
		checarBloque(bloque);
	}
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 696, 696);
	}
}
