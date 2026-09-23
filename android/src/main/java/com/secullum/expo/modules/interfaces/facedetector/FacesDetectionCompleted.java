package com.secullum.expo.modules.interfaces.facedetector;

import android.os.Bundle;

import java.util.ArrayList;

public interface FacesDetectionCompleted {

  void detectionCompleted(ArrayList<Bundle> faces);

}
