package com.truong.brook.client.activities;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.truong.brook.client.activities.home.HomePlace;

@WithTokenizers({HomePlace.Tokenizer.class})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {

}
