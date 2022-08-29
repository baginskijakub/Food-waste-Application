import mediator.Server;
import model.*;

import java.io.IOException;

public class RunServer
{
  public static void main(String[] args)
      throws ClassNotFoundException, IOException
  {
    Model model = new ModelManager();
    Server server = new Server(model);

  }
}
