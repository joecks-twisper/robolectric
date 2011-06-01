package com.xtremelabs.robolectric.shadows;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.internal.Implementation;
import com.xtremelabs.robolectric.internal.Implements;

@Implements( ResolveInfo.class )
public class ShadowResolveInfo {

	private String label;
	
	@Implementation
	public String loadLabel( PackageManager mgr ) { return label; }
	
	/**
	 * Non-Android accessor used to set the value returned by {@link loadLabel}
	 */
	public void setLabel( String l ) { label = l; }
	
	/**
	 * Non-Android accessor used for creating ResolveInfo objects
	 * @param displayName
	 * @param packageName
	 * @return
	 */
	public static ResolveInfo newResolveInfo( String displayName, String packageName ) {
			
		ResolveInfo resInfo = new ResolveInfo();
		ActivityInfo actInfo = new ActivityInfo();
		actInfo.packageName = packageName;
		resInfo.activityInfo = actInfo;
		
		ShadowResolveInfo shResolve = Robolectric.shadowOf(resInfo );
		shResolve.setLabel( displayName );
		return resInfo;
	}
}
