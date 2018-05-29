package de.dertyp7214.appdetails;

import android.content.Context;

import com.danielstone.materialaboutlibrary.MaterialAboutFragment;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;

public class FragmentAbout extends MaterialAboutFragment {

    public FragmentAbout(){

    }

    @Override
    protected MaterialAboutList getMaterialAboutList(final Context activityContext) {
        MaterialAboutCard card = new MaterialAboutCard.Builder()
                .title("Authors")
                .addItem(new MaterialAboutActionItem.Builder()
                        .text("Main Author")
                        .subText("Josua Lengwenath")
                        .build())
                .build();

        return new MaterialAboutList.Builder()
                .addCard(card)
                .build();
    }

    @Override
    protected int getTheme() {
        return R.style.AppTheme_MaterialAboutActivity_Fragment;
    }

}