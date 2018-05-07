package br.com.lFahning;

import br.com.lFahning.view.TelaConfiguracao;
import com.alee.laf.WebLookAndFeel;

/**
 *
 * @author Rafael Carvalhal
 */
public class AppMain implements Runnable {
    
    public static void main(String[] args) {
        new AppMain().run();
    }

    @Override
    public void run() {
        WebLookAndFeel.install();
        new TelaConfiguracao();
    }
    
}
