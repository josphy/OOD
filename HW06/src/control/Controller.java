package control;

import java.awt.EventQueue;
import model.*;
import view.*;
import provided.util.*;

/**
 * Controller class to integrate operations in model and view through adapters
 * @author GPS
 */
public class Controller {
	/**
	 * instantiates model
	 */
	private PlayerModel model;

	/**
	 * instantiates view
	 */
	private PlayerGUI<ABCInstrument> view;

	/**
	 * Default Constructor
	 */
	public Controller() {

		model = new PlayerModel(new IViewAdapter() {
			/** 
			 * getTextFile->take out content in the JTextPanel(Text Area) which store file content
			 * setText->set text view's text area
			 * @param content
			 */
			@Override
			public void setTxtArea(String content) {
				view.getTextFile().setText(content);
			}

			/** 
			 * add instrument into JComboBox
			 * @param instrument
			 */
			@Override
			public void addInstrument(ABCInstrument instrument) {
				view.addInstrument(instrument);
			}
		});

		view = new PlayerGUI<ABCInstrument>(new IModelAdapter<ABCInstrument>() {
			/** 
			 * load abc file using full path ("/songs/" + txtInput.getText() + ".abc")
			 * @param filename:partial filename
			 */
			@Override
			public void Load(String filename) {
				model.LoadFile(filename);
			}

			/** 
			 * Parse->transfer host to String using toString visitor
			 * @see view.IModelAdapter#Parse()
			 */
			@Override
			public void Parse() {
				String parsedString = model.parseToString();
				view.getTextParsed().setText(parsedString);
			}

			/** 
			 * Play
			 * @see view.IModelAdapter#Parse()
			 */
			@Override
			public void Play(ABCInstrument instrument) {
				model.Play(instrument);
			}

			/** 
			 * Stop
			 * @see view.IModelAdapter#Parse()
			 */
			@Override
			public void Stop() {
				model.stop();
			}
		});
	}

	/**
	 * start controller
	 */
	public void start() {
		ABCInstrument[] instrumentArray = ABCUtil.Singleton.getInstruments();
		for (ABCInstrument instrument : instrumentArray) {
			view.addInstrument(instrument);
		}
		model.start();
		view.start();
	}

	/**
	 * Main function
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller controller = new Controller(); // instantiate the system
					controller.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
