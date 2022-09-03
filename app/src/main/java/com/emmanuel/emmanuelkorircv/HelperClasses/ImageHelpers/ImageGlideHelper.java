package com.emmanuel.emmanuelkorircv.HelperClasses.ImageHelpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.emmanuel.emmanuelkorircv.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import xyz.schwaab.avvylib.AvatarView;

public class ImageGlideHelper {

    private Bitmap bitmapx;
//-----------------------------------------------------------------------------------------------------------------------//

    public void load_And_Cache_Dp (Context context , CircleImageView Profile_dp , String uri )
    {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.user)
                .showImageForEmptyUri(R.drawable.user)
                .showImageOnFail(R.drawable.user)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .build();

        ImageAware imageAware = new ImageAware() {
            @Override
            public int getWidth() {
                return Profile_dp.getWidth();
            }

            @Override
            public int getHeight() {
                return Profile_dp.getHeight();
            }

            @Override
            public ViewScaleType getScaleType() {
                return null;
            }

            @Override
            public View getWrappedView() {
                return Profile_dp.getRootView();
            }

            @Override
            public boolean isCollected() {
                return Profile_dp.getAdjustViewBounds();
            }

            @Override
            public int getId() {
                return Profile_dp.getId();
            }

            @Override
            public boolean setImageDrawable(Drawable drawable) { return false; }

            @Override
            public boolean setImageBitmap(Bitmap bitmap) {
                return false;
            }
        };

        ImageSize  targetSize = new ImageSize(imageAware.getWidth(), imageAware.getHeight()); // result Bitmap will be fit to this size

        ImageLoader.getInstance().loadImage(uri, targetSize,options, new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                Toasty.info(context,failReason.toString(),Toasty.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                Profile_dp.setImageBitmap(loadedImage);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
             //    Toasty.info(context,"cancelled",Toasty.LENGTH_SHORT).show();
                ImageLoader.getInstance().displayImage(uri, Profile_dp, options, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {

                    }
                }, new ImageLoadingProgressListener() {
                    @Override
                    public void onProgressUpdate(String imageUri, View view, int current, int total) {
                    }
                });

            }
        });


    }

//------------------------------------------------------------------------------------------------------------------------------//
//-----------------------------------------------------------------------------------------------------------------------//

    public void load_And_Cache_Dp (Context context , AvatarView Profile_dp , String uri )
    {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.user)
                .showImageForEmptyUri(R.drawable.user)
                .showImageOnFail(R.drawable.user)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .build();


        ImageAware imageAware = new ImageAware() {
            @Override
            public int getWidth() {
                return Profile_dp.getWidth();
            }

            @Override
            public int getHeight() {
                return Profile_dp.getHeight();
            }

            @Override
            public ViewScaleType getScaleType() {
                return null;
            }

            @Override
            public View getWrappedView() {
                return Profile_dp.getRootView();
            }

            @Override
            public boolean isCollected() {
                return Profile_dp.getAdjustViewBounds();
            }

            @Override
            public int getId() {
                return Profile_dp.getId();
            }

            @Override
            public boolean setImageDrawable(Drawable drawable) { return false; }

            @Override
            public boolean setImageBitmap(Bitmap bitmap) {
                return false;
            }
        };

        ImageSize  targetSize = new ImageSize(imageAware.getWidth(), imageAware.getHeight()); // result Bitmap will be fit to this size

        ImageLoader.getInstance().loadImage(uri, targetSize ,options, new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                Toasty.info(context,failReason.toString(),Toasty.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                try {
                    Profile_dp.setImageBitmap(loadedImage);
                }catch (Exception e)
                {
                    Log.e("Image loader cache",e.getMessage());
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                ImageLoader.getInstance().displayImage(uri, Profile_dp, options, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {

                    }
                }, new ImageLoadingProgressListener() {
                    @Override
                    public void onProgressUpdate(String imageUri, View view, int current, int total) {

                    }
                });

            }
        });
    }

//------------------------------------------------------------------------------------------------------------------------------//


    public void load_And_Cache_image (Context context , ImageView imageView , String uri )
    {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.user)
                .showImageForEmptyUri(R.drawable.user)
                .showImageOnFail(R.drawable.user)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .build();

         ImageAware imageAware = new ImageAware() {
            @Override
            public int getWidth() {
                return imageView.getWidth();
            }

            @Override
            public int getHeight() {
                return imageView.getHeight();
            }

            @Override
            public ViewScaleType getScaleType() {
                return null;
            }

            @Override
            public View getWrappedView() {
                return imageView.getRootView();
            }

            @Override
            public boolean isCollected() {
                return imageView.getAdjustViewBounds();
            }

            @Override
            public int getId() {
                return imageView.getId();
            }

            @Override
            public boolean setImageDrawable(Drawable drawable) { return false; }

            @Override
            public boolean setImageBitmap(Bitmap bitmap) { return false;}
        };




        ImageSize targetSize = new ImageSize(imageAware.getWidth(), imageAware.getHeight()); // result Bitmap will be fit to this size

        ImageLoader.getInstance().loadImage(uri, targetSize, options, new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                imageView.setImageBitmap(loadedImage);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                ImageLoader.getInstance().displayImage(uri, imageView, options, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {

                    }
                }, new ImageLoadingProgressListener() {
                    @Override
                    public void onProgressUpdate(String imageUri, View view, int current, int total) {

                    }
                });
            }
        });
        }

//------------------------------------------------------------------------------------------------------------------------------------------//


}
