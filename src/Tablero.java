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
							//coronar((Bloque)e.getSource());
							int x = ((Bloque) e.getSource()).getXB();
							int y = ((Bloque) e.getSource()).getYB();
							System.out.println("Ficha: "+((Bloque) e.getSource()).getFicha());
							System.out.println("X: "+((Bloque) e.getSource()).getXB());
							System.out.println("Y: "+((Bloque) e.getSource()).getYB());
							boolean seMueve = mover((Bloque)e.getSource());
							if(seMueve){
									
							}
							System.out.println(""+seMueve);
							
							
							panel.setColor2();
							//((Bloque) e.getSource()).setFicha(2);
							//System.out.println(turno);
							int[] temp=checarBloque((Bloque) e.getSource());
							System.out.println("UpLeft"+temp[0]);
							System.out.println("UpRight"+temp[1]);
							System.out.println("DownLeft"+temp[2]);
							System.out.println("DownRight"+temp[3]);
							turno=-1;
						}
						
						else if(((Bloque) e.getSource()).getFicha()<=turno && turno==-1){
							int x = ((Bloque) e.getSource()).getXB();
							int y = ((Bloque) e.getSource()).getYB();
							//coronar((Bloque)e.getSource());
							System.out.println("Ficha: "+((Bloque) e.getSource()).getFicha());
							System.out.println("X: "+((Bloque) e.getSource()).getXB());
							System.out.println("Y: "+((Bloque) e.getSource()).getYB());
							boolean seMueve = mover((Bloque)e.getSource());
							System.out.println(""+seMueve);
							//System.out.println(""+posMov[0]);
							//System.out.println(""+posMov[1]);
							panel.setColor1();
							//((Bloque) e.getSource()).setFicha(-2);
							
							System.out.println(turno);
							int[] temp=checarBloque((Bloque) e.getSource());
							System.out.println("UpLeft"+temp[0]);
							System.out.println("UpRight"+temp[1]);
							System.out.println("DownLeft"+temp[2]);
							System.out.println("DownRight"+temp[3]);
							turno=1;
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
	public void coronar(Bloque bloque){
		int ficha = bloque.getFicha();
		bloque.setFicha(2*ficha);
		repaint();
	}
	
	public int[] checarBloque(Bloque bloque){
		int x = bloque.getXB();
		int y = bloque.getYB();
		int[] temp = new int[4];
		if((x>0 && x<7)&&(y>0 && y<3)){
                    if((x%2)==1){
                        temp[0]=tablero[x-1][y-1].getFicha();
			temp[1]=tablero[x+1][y-1].getFicha();
			temp[2]=tablero[x-1][y].getFicha();
			temp[3]=tablero[x+1][y].getFicha();
                    }
                    if((x%2)==0){
                        temp[0]=tablero[x-1][y].getFicha();
			temp[1]=tablero[x+1][y].getFicha();
			temp[2]=tablero[x-1][y+1].getFicha();
			temp[3]=tablero[x+1][y+1].getFicha();
                    }
                }    
		//checa si la fila es la fila 0
                else if(y==0){
			if((x%2)==1){
                            if(x!=7){
				temp[0]=3;
				temp[1]=3;
				temp[2]=tablero[x-1][y].getFicha();
				temp[3]=tablero[x+1][y].getFicha();
                            }
                            else{
                                temp[0]=3;
				temp[1]=3;
				temp[2]=tablero[x-1][y].getFicha();
				temp[3]=3;
                            }
			}
                        else{
                            if(x!=0){
                               temp[0]=tablero[x-1][y].getFicha();
				temp[1]=tablero[x+1][y].getFicha();
				temp[2]=tablero[x-1][y+1].getFicha();
				temp[3]=tablero[x+1][y+1].getFicha();
                            }
                            else{
                                temp[0]=3;
				temp[1]=tablero[x+1][y].getFicha();
				temp[2]=3;
				temp[3]=tablero[x+1][y+1].getFicha();
                            }    
                        }
			
		}
		else if(y==3){
			if((x%2)==0){
                            if(x!=0){
				temp[0]=tablero[x-1][y].getFicha();
				temp[1]=tablero[x+1][y].getFicha();
				temp[2]=3;
				temp[3]=3;
                            }
                            else{
                                temp[0]=3;
				temp[1]=tablero[x+1][y].getFicha();
				temp[2]=3;
				temp[3]=3;
                            }
			}
                        else{
                            if(x!=7){
                                temp[0]=tablero[x-1][y-1].getFicha();
				temp[1]=tablero[x+1][y-1].getFicha();
				temp[2]=tablero[x-1][y].getFicha();
				temp[3]=tablero[x+1][y].getFicha();
                            }
                            else{
                                temp[0]=tablero[x-1][y-1].getFicha();
				temp[1]=3;
				temp[2]=tablero[x-1][y].getFicha();
				temp[3]=3;
                            }
                        }
		}
                
		//checa si la fila es la fila 3
		else if(x==0){
                    temp[0]=3;
                    temp[1]=tablero[x+1][y].getFicha();
                    temp[2]=3;
                    temp[3]=tablero[x+1][y+1].getFicha();	
		}
                else if(x==7){
                    temp[0]=tablero[x-1][y+1].getFicha();
                    temp[1]=3;
                    temp[2]=tablero[x-1][y].getFicha();
                    temp[3]=3;
                }
                else{
                    System.out.println("Error al checar");
                    return null;
                }
		return temp;
	}
	
	public boolean mover(Bloque bloque){
		boolean seMueve = false;
		int[] temp = checarBloque(bloque);
		int ficha = bloque.getFicha();
		int x = bloque.getXB();
		int y = bloque.getYB();
		int UL = temp[0];
		int UR = temp[1];
		int DL = temp[2];
		int DR = temp[3];
		if(ficha == 1){
			if(UL == 0 && UR == 0){
				if((x%2)==0){
					tablero[x-1][y].setColor();
					tablero[x+1][y].setColor();
					seMueve = true;
				}
				else if((x%2)==1){
					tablero[x-1][y-1].setColor();
					tablero[x+1][y-1].setColor();
					seMueve = true;
				}
			}
			else if(UL == 0 && UR != 0){
				if((x%2)==0){
					tablero[x-1][y].setColor();
					seMueve = true;
					}
				else {
					tablero[x-1][y-1].setColor();
					seMueve = true;
				}
			}
			else if(UL != 0 && UR ==0){
				if((x%2)==0){
					tablero[x+1][y].setColor();
					seMueve = true;
				}
				else {
					tablero[x+1][y-1].setColor();
					seMueve = true;
				}
			}
		}
		else{
			if(DL == 0 && DR == 0){
				if((x%2)==0){
					tablero[x-1][y+1].setColor();
					tablero[x+1][y+1].setColor();
					seMueve = true;
				}
				else if((x%2)==1){
					tablero[x-1][y].setColor();
					tablero[x+1][y].setColor();
					seMueve = true;
				}
			}
			else if(DL == 0 && DR != 0){
				if((x%2)==0){
					tablero[x-1][y+1].setColor();
					seMueve = true;
				}
				else {
					tablero[x-1][y].setColor();
					seMueve = true;
				}
			}
			else if(DL != 0 && DR ==0){
				if((x%2)==0){
					tablero[x+1][y].setColor();
					seMueve = true;
				}
				else {
					tablero[x+1][y+1].setColor();
					seMueve = true;
				}
			}
			else{
				seMueve = false;
			}
		}
		return seMueve;
	}
	/*public int[] moverseBlue(Bloque bloque){
		int[] posMov = new int[2];
		int[] temp = checarBloque(bloque);
		int ficha = bloque.getFicha();
		int x = bloque.getXB();
		int y = bloque.getYB();
		int UL = temp[0];
		int UR = temp[1];
		if((UL*ficha)>0 && (UR*ficha)>0){
			if((x%2)==0 && UL != 3){
				if(UL==0){
					tablero[x-1][y].setColor();
					posMov[0] = 1;
				}
				if(UR==0){
					tablero[x+1][y].setColor();
					posMov[1]=1;
				}
			}
			else if((x%2)==1 && UR != 3){
				if(UL==0){
					tablero[x-1][y-1].setColor();
					posMov[0]=1;
				}
				if(UR==0){
					tablero[x+1][y-1].setColor();
					posMov[1]=1;
				}
			}
			else if(x==0){
				tablero[x+1][y].setColor();
				posMov[0]=1;
			}
			else{
				tablero[x-1][y+1].setColor();
				posMov[1]=1;
			}
			return posMov;
		}
		else{
			//comer();
		}
		return posMov;
	}*/
	/*public void moverseRed(Bloque bloque){
		int[] temp = checarBloque(bloque);
		int ficha = bloque.getFicha();
		int x = bloque.getXB();
		int y = bloque.getYB();
		int DL = temp[2];
		int DR = temp[3];
		if((x%2)==0 && DL != 3){
			tablero[x-1][y+1].setColor();
			tablero[x+1][y+1].setColor();
		}
		else if((x%2)==1 && DR != 3){
			tablero[x-1][y].setColor();
			tablero[x+1][y].setColor();
		}
		else if(x==0){
			tablero[x+1][y+1].setColor();
		}
		else{
			tablero[x-1][y].setColor();
		}
		
	}*/
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 696, 696);
	}
}
