/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datosm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

/**
 *
 * @author MEL
 */
public class funciones {
    private BufferedReader br;
    
   public void habilitar(String d1){
    String dir4=d1;
    File archivoo=new File(dir4);
    archivoo.delete();
   }
   
   public double[][][] rellenar(){
       double A[][][]= new double[20][15][2];
       
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[i].length;j++){
                for(int k=0;k<A[i][j].length;k++){
                 A[i][j][k]=0.0;
            }}}
        
        return A;
   }
   
   public int ip(int p1,int i){
       int p=p1-(130-6*i);
       return p;
   }
  
   public int jp(int p1,int j,String l){
       int p;
       if(l.equals("N")){
       p=p1-(45-6*j);
       }
       else
       {
       p=-(p1-(45-6*j));  
       }
       return p;
   }
   
   public double direccion(String po,int i){
       Double angulo=Double.parseDouble(po.substring(0+9*i,3+9*i));
       return angulo;                
   }
    
   public double intensidad(String po,int i){
       Double intensidad=Double.parseDouble(po.substring(3+9*i,5+9*i)); 
       return intensidad;                
   }                    
 
    
  //----------------------------------------------------------------------------------
   public void limpiarrr() {
    try{
    String dir1="E:\\documentillos\\netbeans\\datosm\\src\\txt\\t1.txt";
    String dir2="E:\\documentillos\\netbeans\\datosm\\src\\txt\\dinte.txt ";
    habilitar(dir2);
    
    String cadena,cad,nivel,lat;
    String nnivel="300"; //nivel a buscar
    double p[][][]=rellenar();
    
    FileReader fr=new FileReader(dir1);
    br=new BufferedReader(fr);
    File archivo=new File(dir2);
    
        try (FileWriter escribir = new FileWriter(archivo,true)) {
            escribir.write("\t\t Tabla Direcion -Intensidad velocidad viento (F=300 )\n");
            escribir.write("\t\t ----------------------------------------------- \n");
           
            while((cadena=br.readLine())!=null){
                nivel=cadena.substring(1,4);
                lat=cadena;
                
                if(nivel.equals(nnivel)){
                                            
                    String po=cadena.substring(5);// a partir cadena[5]
                    
                    int num=po.length();//longitud cadena
                    int rs=num/8;//division entera
                    escribir.write(" ");
                    
                    for (int i=0;i<rs;i++){
                        
                        Double angulo=direccion(po,i);
                        Double intensidad=intensidad(po,i); 
                                         
                                      
                        System.out.print("angulo:"+angulo+"-intensidad:"+intensidad+"  ");
                        escribir.write(po.substring(0+9*i,3+9*i)+"-"+po.substring(3+9*i,5+9*i)+"   ");
                       }
                    escribir.write("\n");
                     System.out.print("\n");
                }
                
                if(lat.endsWith("KT") ){
                    cad=br.readLine();
                    System.out.print("\n");
                    escribir.write("\n      "+cad.substring(6)+"\n");
                    escribir.write(cad.substring(0,4)+"");
                    }
                if((lat.substring(3,4)).equals("N") || (lat.substring(3,4)).equals("S") ){
                    escribir.write(""+lat.substring(0, 4)+"");
                    }
            }   }
   }
    catch(Exception e){
    System.out.println("Error al escribir");
    }}
   
   
      
   public void matriz() {
    double p[][][]=new double[15][20][2];
    double D[][]=new double[13][18];
    
    try{
    String dir1="E:\\documentillos\\netbeans\\datosm\\src\\txt\\t1.txt";
    String cadena,nivel,lat;
    String nnivel="300"; //nivel a buscar
   
    
    FileReader fr=new FileReader(dir1);
    br=new BufferedReader(fr);
    
    
           int k=0;
            while((cadena=br.readLine())!=null){
                nivel=cadena.substring(1,4);
                lat=cadena;
               
                
                if(nivel.equals(nnivel)){
                    
                    double angulo,intensidad,vy,vx;                        
                    String po=cadena.substring(5);// a partir cadena[5]
                    
                    int num=po.length();//longitud cadena
                    int rs=num/8;//division entera
                
                                   for (int i=0;i<rs;i++){
                         angulo=direccion(po,i);
                         intensidad=intensidad(po,i)*1.850; // km
                        
                         vy=Math.round((-intensidad*Math.cos((angulo*Math.PI/180))));
                         vx=Math.round((-intensidad*Math.sin((angulo*Math.PI/180))));
                         
                        
                         if(k >= 1 && k<= 4){
                         if(i >= 0 && i<= 5){
                                  p[k+2][i+8][0]=vy;
                                  p[k+2][i+8][1]=vx;           
                         }}
                         
                         if(k >= 5 && k<= 7){
                         if(i >= 0 && i<= 6){
                                  p[k+2][i+6][0]=vy;
                                  p[k+2][i+6][1]=vx;                             
                         }}
                         
                         if(k >= 8 && k<= 12){
                         if(i >= 0 && i<= 5){
                                  p[k+2][i+14][0]=vy;
                                  p[k+2][i+14][1]=vx;                             
                         }}
                         
                         if(k >= 13 && k<= 16){
                         if(i >= 0 && i<= 5){
                                  p[k-10][i+2][0]=vy;
                                  p[k-10][i+2][1]=vx;                             
                         }}
                         
                         if(k >= 17 && k<= 19){
                         if(i >= 0 && i<= 5){
                                  p[k-10][i+13][0]=vy;
                                  p[k-10][i+13][1]=vx;                            
                         }}
                         
                         if(k >= 20 && k<= 24){
                         if(i >= 0 && i<= 4){
                                  p[k-10][i+9][0]=vy;
                                  p[k-10][i+9][1]=vx;                            
                         }}
                         
                         if(k >= 25 && k<=27){
                         if(i >= 0 && i<= 5){
                                  p[k-25][i][0]=vy;
                                  p[k-25][i][1]=vx;                            
                         }}
                             }
                }
                
               
                
                if((lat.substring(3,4)).equals("N") || (lat.substring(3,4)).equals("S") ){
                   k++;
                   }
            }
        }
        
    catch(Exception e){
    System.out.println("Error al escribir");
    }
   //--Mostrar matriz-- p[][][1] vx
   //                   p[][][0] vy
    System.out.println("\t\tMatriz");
    System.out.println("\t\t-------");
   System.out.println("\nMatriz  p[][][1] vx\n");
   
   for(int i=0; i<p.length; i++){
        System.out.print("[");
        for(int j=0; j<p[i].length; j++){
            System.out.print(p[i][j][1]+"    ");}
        System.out.print("]");
        System.out.printf("\n");}
   
   System.out.println("\nMatriz  p[][][0] vy\n");
    for(int i=0; i<p.length; i++){
        System.out.print("[");
        for(int j=0; j<p[i].length; j++){
            System.out.print(p[i][j][0]+"    ");}
        System.out.print("]");
        System.out.printf("\n");}
   
    System.out.println("\nDivergencia  D[][0] \n");
    
   for(int i=0; i<p.length-2; i++){
        System.out.print("[");
        for(int j=0; j<p[i].length-2; j++){
            if(p[i+1][j+2][0]==0 && p[i+1][j+2][1]==0){
                D[i][j]=0;
            }
            else if(p[i+1][j][0]==0 && p[i+1][j][1]==0){
                D[i][j]=0;}
            else if(p[i][j+1][0]==0 && p[i][j+1][1]==0){
                D[i][j]=0;}
            else if(p[i+2][j+1][0]==0 && p[i+2][j+1][1]==0){
                D[i][j]=0;}
            else{
                D[i][j]=-((p[i+1][j+2][1]-p[i+1][j][1])/(105.71)+(p[i][j+1][0]-p[i+2][j+1][1])/(111.12));
                        
            }
            
            System.out.print(D[i][j]+"    ");}
        System.out.print("]");
        System.out.printf("\n");}
   
   
   }
   
   
   
   
   public void vectores() {
    try{
     
 

    String dir1="E:\\documentillos\\netbeans\\datosm\\src\\txt\\t1.txt";
    String dir2="E:\\documentillos\\netbeans\\datosm\\src\\txt\\vectores.txt ";
    habilitar(dir2);
    String cadena,cad,nivel,lat;
    String nnivel="300"; //nivel a buscar
    
  
    
    FileReader fr=new FileReader(dir1);
    br=new BufferedReader(fr);
    File archivo=new File(dir2);
    
      
        try (FileWriter escribir = new FileWriter(archivo,true)) {
            escribir.write("\t\t Tabla vectores (u,v) viento (F=300 )\n");
            escribir.write("\t\t --------------------------------------- \n");
           
            while((cadena=br.readLine())!=null){
                nivel=cadena.substring(1,4);
                lat=cadena;
                
                if(nivel.equals(nnivel)){
                    double angulo,intensidad,vy,vx;                            
                    String po=cadena.substring(5); 
                    int num=po.length();
                    int rs=num/8;
                    escribir.write(" ");
                    for (int i=0;i<rs;i++){
                        
                          angulo=direccion(po,i);
                         intensidad=intensidad(po,i)*1.850; // km
                        
                         vy=Math.round((-intensidad*Math.cos((angulo*Math.PI/180))));
                         vx=Math.round((-intensidad*Math.sin((angulo*Math.PI/180))));
                        
                         
                       String dd=(Double.toString(vx));
                       String gg=(Double.toString(vy));
                      escribir.write(" ("+dd+","+gg+")");
                      
                       }
                    escribir.write("\n");
                }
                if(lat.endsWith("KT") ){
                    cad=br.readLine();
                    escribir.write("\n      "+cad.substring(6)+"\n");
                    escribir.write(cad.substring(0,4)+"");
               }
                  if((lat.substring(3,4)).equals("N") || (lat.substring(3,4)).equals("S") ){
                    escribir.write(""+lat.substring(0, 4)+"");
                                      
                }
            }   }
   }
    catch(Exception e){
    System.out.println("Error al escribir");
    }}
   
   public void intensidad() {
    try{
    String cadena;
    String cad;
    String nivel;

    String dir1="E:\\documentillos\\netbeans\\datosm\\src\\txt\\t1.txt";
    String dir2="E:\\documentillos\\netbeans\\datosm\\src\\txt\\intensidad.txt ";
    String nnivel="300"; //nivel a buscar
    String lat,lon,latitud;
  
    
    FileReader fr=new FileReader(dir1);
    br=new BufferedReader(fr);
    File archivo=new File(dir2);
    
      
        try (FileWriter escribir = new FileWriter(archivo,true)) {
            escribir.write("\t\t Tabla Intensidad velocidad viento (F=300 )\n");
            escribir.write("\t\t ------------------------------------- \n");
           
            while((cadena=br.readLine())!=null){
                nivel=cadena.substring(1,4);
                lat=cadena;
                
                if(nivel.equals(nnivel)){
                  
                                  
                    String po=cadena.substring(5); 
                    int num=po.length();
                    int rs=num/8;
                    escribir.write(" ");
                    for (int i=0;i<rs;i++){
                        escribir.write("   "+po.substring(3+9*i,5+9*i)+"    ");
                                       }
                    escribir.write("\n");
                }
                if(lat.endsWith("KT") ){
                    cad=br.readLine();
                
                    escribir.write("\n      "+cad.substring(6)+"\n");
                    escribir.write(cad.substring(0,4)+"");
               }
                  if((lat.substring(3,4)).equals("N") || (lat.substring(3,4)).equals("S") ){
                    cad=br.readLine();
                    escribir.write(""+lat.substring(0, 4)+"");
                                      
                }
            }   }
   }
    catch(Exception e){
    System.out.println("Error al escribir");
    }}
   
   public void intensidad2() {
    try{
    String dir4="C:\\Users\\MEL\\Documents\\NetBeansProjects\\datosm\\src\\txt\\intensidad.txt ";
   File archivoo=new File(dir4);
    archivoo.delete();
    
    String cadena;
    String cad;
    String nivel;

    String dir1="E:\\documentillos\\netbeans\\datosm\\src\\txt\\t1.txt";
    String dir2="E:\\documentillos\\netbeans\\datosm\\src\\txt\\intensidad.txt ";
    String nnivel="300"; //nivel a buscar
    String lat,lon,latitud;
  
    
    FileReader fr=new FileReader(dir1);
    br=new BufferedReader(fr);
    File archivo=new File(dir2);
    
      
        try (FileWriter escribir = new FileWriter(archivo,true)) {
            escribir.write("\t\t Tabla Intensidad velocidad viento (F=300 )\n");
            escribir.write("\t\t ------------------------------------- \n");
           
            while((cadena=br.readLine())!=null){
                nivel=cadena.substring(1,4);
                lat=cadena;
                
                if(nivel.equals(nnivel)){
                                                
                    String po=cadena.substring(5); 
                    int num=po.length();
                    int rs=num/8;
                    escribir.write(" ");
                    for (int i=0;i<rs;i++){
                        escribir.write("   "+po.substring(3+9*i,5+9*i)+"    ");
                    }
                    escribir.write("\n");
                }
                
                   if(lat.endsWith("KT") ){
                     escribir.write("\n");            
                    }
            }   
        }
   }
    catch(Exception e){
    System.out.println("Error al escribir");
    }}
   
   public void direccion() {
    try{
    String cadena;
    String cad;
    String nivel;

    String dir1="E:\\documentillos\\netbeans\\datosm\\src\\txt\\t1.txt";
    String dir2="E:\\documentillos\\netbeans\\datosm\\src\\txt\\direccion.txt ";
    String nnivel="300"; //nivel a buscar
    String lat,lon,latitud;
  
    
    FileReader fr=new FileReader(dir1);
    br=new BufferedReader(fr);
    File archivo=new File(dir2);
    
      
        try (FileWriter escribir = new FileWriter(archivo,true)) {
            escribir.write("\t\t Tabla Direccion viento (F=300 )\n");
            escribir.write("\t\t --------------------------------- \n");
           
            while((cadena=br.readLine())!=null){
                nivel=cadena.substring(1,4);
                lat=cadena;
                
                if(nivel.equals(nnivel)){
                  
                                  
                    String po=cadena.substring(5); 
                    int num=po.length();
                    int rs=num/8;
                    escribir.write(" ");
                    for (int i=0;i<rs;i++){
                        escribir.write("  "+po.substring(0+9*i,3+9*i)+"    ");
                                       }
                    escribir.write("\n");
                }
                if(lat.endsWith("KT") ){
                    cad=br.readLine();
                
                    escribir.write("\n      "+cad.substring(6)+"\n");
                    escribir.write(cad.substring(0,4)+"");
               }
                  if((lat.substring(3,4)).equals("N") || (lat.substring(3,4)).equals("S") ){
                    cad=br.readLine();
                    escribir.write(""+lat.substring(0, 4)+"");
                                      
                }
            }   }
   }
    catch(Exception e){
    System.out.println("Error al escribir");
    }}

   //---------------------------------------------------------
  
}
    

