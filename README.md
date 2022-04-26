# PIX QR Code generator

Este projeto é direcionado a desenvolvedores que têm a necessidade de escrever código capaz de gerar dados de QR Codes compatíveis com a especificação PIX. 

Conforme a especificação, é possível gerar QR Codes estáticos ou dinâmicos.

## Gerando um QR Code estático

A API fornecida por este projeto possibilita a geração dos dados por duas formas disponíveis: 

### Utilizando uma DSL
  
  O projeto disponibiliza uma DSL (_Domain Specific Language_) que adapta a criação dos QR Codes e impossibilita a criação de um QR Code com dados errados, pois não permite que os dados sejam gerados com informações faltantes ou formatadas incorretamente. 
  
  Um exemplo de uso desta DSL é o seguinte:

  
  ```java
  import static com.github.alesaudate.pix.qrcode.QRCodeBuilder.staticQRCode;

  public class Pix {
      
      public static void main(String[] args) {
          String staticQRCode =
                  staticQRCode()
                          .merchantAccountInformation()
                          .merchantKey("123e4567-e12b-12d1-a456-426655440000")
                          .unknownMerchantCategoryCode()
                          .merchantName("Fulano de Tal")
                          .merchantCity("BRASILIA")
                          .build();
          
          System.out.println(staticQRCode); 
      }      
  }
  ```

  Este código irá imprimir o mesmo QRCode disponível na página 16 da [espeficicação do PIX](https://www.bcb.gov.br/content/estabilidadefinanceira/forumpireunioes/AnexoI-PadroesParaIniciacaodoPix.pdf), ou seja:

  `00020126580014br.gov.bcb.pix0136123e4567-e12b-12d1-a456-4266554400005204000053039865802BR5913Fulano de Tal6008BRASILIA62070503***63041D3D`

  Observe que os métodos desta DSL só podem ser utilizados nesta ordem, e não é possível invocar o método `build` sem que todos os métodos anteriores tenham sido invocados anteriormente, desta forma impossibilitando a geração de um QR Code com dados faltantes. Além disso, todos os métodos fazem verificação acerca da validade das entradas, também impossibilitando a criação de um QR Code PIX com dados gerados de forma incorreta. 
  
### Utilizando um POJO 

  Se por algum motivo o desenvolvedor não quiser / não puder utilizar a DSL, existe uma abordagem alternativa para criação do objeto através do uso de um POJO. Um exemplo de uso desta API é o seguinte:
  
```java
import com.github.alesaudate.pix.qrcode.StaticQRCode;

public class Pix {

    public static void main(String[] args) {
        StaticQRCode staticQRCode = new StaticQRCode();
        staticQRCode.setDefaultValues();
        staticQRCode.setMerchantAccountInformationKey("123e4567-e12b-12d1-a456-426655440000");
        staticQRCode.setMerchantName("Fulano de Tal");
        staticQRCode.setMerchantCity("BRASILIA");

        System.out.println(staticQRCode.asString());
    }
}
```

Este código produz a mesma saída que o descrito pela DSL, mas observe que ele não apresenta as mesmas restrições. Isto quer dizer que os métodos podem ser invocados em qualquer ordem, e a presença ou ausência dos blocos obrigatórios somente será verificada através da execução do método `asString`.

## Gerando um QR Code dinâmico

### Através da DSL

### Utilizando um POJO