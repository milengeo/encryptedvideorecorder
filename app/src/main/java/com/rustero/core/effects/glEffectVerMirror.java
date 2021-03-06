
package com.rustero.core.effects;


import android.opengl.GLES20;

import com.rustero.core.egl.glCore;

public class glEffectVerMirror extends glEffect {



	protected String getFragmentCode() {
		String result =
			"precision mediump float;\n" +
			"varying vec2 vTextureCoord;" +
			"uniform float uTurn90;" +
			"void main() {" +
			"    vec2 position = vTextureCoord;" +
			"	 if (uTurn90 > 0.5) {" +
			"        position.x = 0.5 - abs(position.x - 0.5);" +
			"    } else {" +
			"        position.y = 0.5 - abs(position.y - 0.5); " +
			"    }" +
			"    vec4 pixel = texture2D(sTexture, position);" +
			"    gl_FragColor = pixel;" +
           	"}";

	return result;
}



    public glEffectVerMirror() {
        super();
		name = NAME_VerticalMirror;
    }



	protected void compileCustom() {
		mTurn90Loc = GLES20.glGetUniformLocation(mProgram, "uTurn90");
		if (mTurn90Loc >= 0)
			checkLocation(mTurn90Loc, "uTurn90");
	}


	protected void drawCustom() {
		if (mTurn90Loc >= 0) {
			GLES20.glUniform1f(mTurn90Loc, mTurn90);
			glCore.checkGlError("glUniform1f");
		}
	}




}
