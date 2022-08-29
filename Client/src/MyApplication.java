import com.sun.webkit.Timer;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelUser;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{

  private Model model;

  @Override public void start(Stage stage) throws Exception
  {
     model = new ModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);


    ViewHandler view = new ViewHandler(viewModelFactory);

    view.start(stage);
  }

  @Override public void stop() throws Exception
  {
    model.clearBag();
    System.exit(0);
  }
}
