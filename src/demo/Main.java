package demo;

import cognitiveservices.api.Emotion;
import cognitiveservices.api.Face;

public class Main {

	final private String EmotionAPIKey = "{write your key here}";
	final private String FaceAPIKey = "{write your key here}";
	
	public static void main(String[] args) {
		System.out.println("================ START ================");
		
		Main process = new Main();
		process.detectEmotion();
		process.detectFace();
		
		//process.followEmotion();
		
		System.out.println("================= END =================");
	}
	
	public void detectEmotion() {
		String emotion;
		//emotion = Emotion.runEmotion(EmotionAPIKey, "assets/out6.png");
		emotion = Emotion.runEmotion(EmotionAPIKey, "http://asylamba.com/public/media/files/sources/out6.png");
		System.out.println(emotion);
	}
	
	public void detectFace() {
		String face;
		//face = Face.runFace(FaceAPIKey, "assets/out6.png");
		//face = Face.runFace(FaceAPIKey, "http://i.f1g.fr/media/figaro/1280x580_crop/2015/09/09/XVM1bf9bc3e-56f3-11e5-8f47-f6275d19194b.jpg");
		face = Face.runFace(FaceAPIKey, "http://anotherwhiskyformisterbukowski.com/wp-content/uploads/2014/08/WW.jpg");
		System.out.println(face);
	}

	public void followEmotion() {
		String emotion;
		for (int i = 0; i < 15; i++) {
			emotion = Face.runFace(FaceAPIKey, "assets/video1/out" + (i+1) + ".png");
			System.out.println("Image out" + (i+1) + ".png : " + emotion);
		}
	}
}
