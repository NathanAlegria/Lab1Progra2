/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1_recursividad;


import javax.swing.JOptionPane;



public class EmailAccount {
 
 private final String Password; 
 private final String DireccionEmail;
 private final String NombreUsuario;
 private final String inbox[];
 
 private static final int INBOX_CAPACITY=10;
 
 public EmailAccount (String Password, String DireccionEmail,String NombreUsuario, String inbox, String Email){
     
     this.DireccionEmail=DireccionEmail;
     this.Password=Password;
     this.NombreUsuario=NombreUsuario;
     
     this.inbox= new String[INBOX_CAPACITY];
 
           
     
 }
 public String getDireccionEmail(){
    return DireccionEmail;
}

  public String getPassword(){
  return Password;
 }
  
  public String getNombreUsuario(){
      return NombreUsuario;
      
  }
   
  public boolean CorreosRecibidos(String mensajes){
      
      for (int i = 0; i<INBOX_CAPACITY ; i++) {
        if (inbox[i]==null){
            inbox[i] = mensajes;
            return true;
        }  
      }
     return false; 
  }
  public void MostrarInbox(){
    
      StringBuilder mensajes = new StringBuilder("Bandeja de entrada:"+NombreUsuario+"\n\n");
      
      boolean Empty= true;
      for(String Correo:inbox){
          if(Correo!=null)
              mensajes.append("-").append(Correo).append("\n");
          
          
      }
      if (Empty){
          mensajes.append("No se encontraron Correos");
      }
      JOptionPane.showMessageDialog(null, mensajes.toString());
  }   
  
  public void EnviarCorreo(EmailAccount Receptor, String mensajes){
     
      boolean Enviado = Receptor.CorreosRecibidos("De:"+this.NombreUsuario+"->"+mensajes);
    
      if(Enviado){
          JOptionPane.showMessageDialog(null,"El mensaje se envio exitosament a:"+Receptor.getNombreUsuario());
      }
      else{
          JOptionPane.showMessageDialog(null,"El correo no se pudo enviar correctamente, Bandeja de entrada llena");
      }
  }
    public static void main(String[]arg){
     
       JOptionPane.showMessageDialog(null, "Bienvenido a nuestro sistema de correos electronicos");
 }
  
}
  
 
 

