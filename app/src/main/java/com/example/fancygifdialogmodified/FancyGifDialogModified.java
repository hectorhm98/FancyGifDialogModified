package com.example.fancygifdialogmodified;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannedString;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Tayyab Tariq on 1/7/2018.
 */

public class FancyGifDialogModified {
    private String title,message,positiveBtnText,negativeBtnText,positiveBtnColor,negativeBtnColor;
    private SpannedString messageBold;
    private Activity activity;
    private FancyGifDialogModifiedListener positiveListener,negativeListener;
    private boolean cancel;
    int gifImageResource;
    static Dialog dialogAux;

    private FancyGifDialogModified(Builder builder){
        this.title=builder.title;
        this.message=builder.message;
        this.messageBold = builder.messageBold;
        this.activity=builder.activity;
        this.positiveListener=builder.positiveListener;
        this.negativeListener=builder.negativeListener;
        this.positiveBtnColor=builder.positiveBtnColor;
        this.negativeBtnColor=builder.negativeBtnColor;
        this.positiveBtnText=builder.positiveBtnText;
        this.negativeBtnText=builder.negativeBtnText;
        this.gifImageResource=builder.gifImageResource;
        this.cancel=builder.cancel;
    }

    public void dismiss(){
        this.dialogAux.dismiss();
    }

    public static class Builder{
        private String title,message,positiveBtnText,negativeBtnText,positiveBtnColor,negativeBtnColor;
        private SpannedString messageBold;
        private Activity activity;
        private FancyGifDialogModifiedListener positiveListener,negativeListener;
        private boolean cancel;
        int gifImageResource;

        public Builder(Activity activity){
            this.activity=activity;
        }

        public Builder setTitle(String title){
            this.title=title;
            return this;
        }

        public Builder setMessage(String message){
            this.message=message;
            return this;
        }

        public Builder setMessageBold(SpannedString message){
            this.messageBold=message;
            return this;
        }

        public Builder setPositiveBtnText(String positiveBtnText){
            this.positiveBtnText=positiveBtnText;
            return this;
        }

        public Builder setNegativeBtnText(String negativeBtnText){
            this.negativeBtnText=negativeBtnText;
            return this;
        }

        public Builder setPositiveBtnBackground(String positiveBtnColor){
            this.positiveBtnColor=positiveBtnColor;
            return this;
        }

        public Builder setNegativeBtnBackground(String negativeBtnColor){
            this.negativeBtnColor=negativeBtnColor;
            return this;
        }

        //set Positive Listener
        public Builder OnPositiveClicked(FancyGifDialogModifiedListener positiveListener){
            this.positiveListener=positiveListener;
            return this;
        }

        //set Negative Listener
        public Builder OnNegativeClicked(FancyGifDialogModifiedListener negativeListener){
            this.negativeListener=negativeListener;
            return this;
        }

        public Builder isCancellable(boolean cancel){
            this.cancel=cancel;
            return this;
        }

        //set GIF Resource
        public Builder setGifResource(int gifImageResource){
            this.gifImageResource=gifImageResource;
            return this;
        }

        public FancyGifDialogModified build(){
            TextView message1,title1;
            ImageView iconImg;
            final Button pBtn,nBtn;
            GifImageView gifImageView;
            final Dialog dialog;
            dialog=new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(cancel);
            dialog.setContentView(R.layout.tt_fancy_gif_dialog);

            //init
            title1= (TextView) dialog.findViewById(R.id.title);
            message1=(TextView)dialog.findViewById(R.id.message);
            pBtn=(Button)dialog.findViewById(R.id.positiveBtn);
            nBtn=(Button)dialog.findViewById(R.id.negativeBtn);
            gifImageView=dialog.findViewById(R.id.gifImageView);
            gifImageView.setImageResource(gifImageResource);
            gifImageView.setAdjustViewBounds(true);

            //values
            title1.setText(title);
            if(message != null) {
                message1.setText(message);
            }
            else {
                message1.setText(messageBold);
            }
            if(positiveBtnText!=null){
                pBtn.setText(positiveBtnText);
            }
            if(negativeBtnText!=null){
                nBtn.setText(negativeBtnText);
            }
            if(positiveBtnColor!=null){
                GradientDrawable bgShape= (GradientDrawable) pBtn.getBackground();
                bgShape.setColor(Color.parseColor(positiveBtnColor));
            }
            if(negativeBtnColor!=null){
                GradientDrawable bgShape= (GradientDrawable) nBtn.getBackground();
                bgShape.setColor(Color.parseColor(negativeBtnColor));
            }
            if(positiveListener!=null){
                pBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        positiveListener.OnClick();
                        dialog.dismiss();
                    }
                });
            }else{
                pBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }

            if(negativeListener!=null){
                nBtn.setVisibility(View.VISIBLE);
                nBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        negativeListener.OnClick();
                        dialog.dismiss();
                    }
                });
            }
            dialogAux = dialog;
            dialogAux.show();

            return new FancyGifDialogModified(this);
        }


    }
}
