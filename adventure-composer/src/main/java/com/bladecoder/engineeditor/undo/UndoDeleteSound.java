package com.bladecoder.engineeditor.undo;

import com.bladecoder.engine.model.InteractiveActor;
import com.bladecoder.engine.model.SoundFX;
import com.bladecoder.engineeditor.Ctx;
import com.bladecoder.engineeditor.model.Project;


public class UndoDeleteSound implements UndoOp {
	private InteractiveActor a;
	private SoundFX s;
	
	public UndoDeleteSound(InteractiveActor a, SoundFX s) {
		this.s = s;
		this.a = a;
	}
	
	@Override
	public void undo() {
		a.addSound(s);
		Ctx.project.setModified(this, Project.NOTIFY_ELEMENT_CREATED, null, s);
	}
}
