package model;

import model.modelData.ModelData;
import model.rawData.RawData;

public interface Data {
  ModelData toModelData();
  RawData toRawData();

}
