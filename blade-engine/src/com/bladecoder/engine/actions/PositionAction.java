/*******************************************************************************
 * Copyright 2014 Rafael Garcia Moreno.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.bladecoder.engine.actions;

import com.badlogic.gdx.math.Vector2;
import com.bladecoder.engine.assets.EngineAssetManager;
import com.bladecoder.engine.model.BaseActor;
import com.bladecoder.engine.model.InteractiveActor;
import com.bladecoder.engine.model.Scene;
import com.bladecoder.engine.model.VerbRunner;

@ActionDescription("Sets actor position.")
public class PositionAction implements Action {
	@ActionProperty( required = true)
	@ActionPropertyDescription("The actor to change his position")	
	private SceneActorRef actor;

	@ActionProperty
	@ActionPropertyDescription("The position to set")
	private Vector2 position;
	
	@ActionProperty
	@ActionPropertyDescription("Sets the position from this actor")	
	private SceneActorRef target;

	@Override
	public boolean run(VerbRunner cb) {
		Scene s = actor.getScene();

		BaseActor a = s.getActor(actor.getActorId(), true);
		
		float x = a.getX();
		float y = a.getY();

		if (position != null) {
			float scale = EngineAssetManager.getInstance().getScale();
			x = position.x * scale; 
			y = position.y * scale;

		} else if(target != null) {
			BaseActor anchorActor = s.getActor(target.getActorId(), true);
			
			x = anchorActor.getX();
			y = anchorActor.getY();
			
			if(anchorActor instanceof InteractiveActor) {
				Vector2 refPoint = ((InteractiveActor) anchorActor).getRefPoint();
				x+= refPoint.x;
				y+= refPoint.y;
			}
		}
		
		a.setPosition(x, y);

		return false;
	}

}
