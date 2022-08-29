package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class ViewCreator
{
    private Map<String, ViewController> map = new HashMap<>();

    public ViewCreator ()
    {

    }

    public ViewController getViewController(String id) throws IOException
    {
        ViewController viewController = map.get(id);
        if (viewController == null)
        {
            viewController = loadFromFXML(id + ".fxml");


            map.put(id, viewController);
        }
        viewController.reset();
        return viewController;
    }

    protected abstract void initViewController (ViewController controller, Region root);

    private ViewController loadFromFXML (String fxmlFile) throws IOException
    {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();

        ViewController viewController = loader.getController();
        initViewController(viewController, root);
        return viewController;
    }
}
