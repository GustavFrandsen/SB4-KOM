package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
public class CollisionDetectorTest {

 private CollisionDetector collisionDetector;
 private Entity entity1;
 private Entity entity2;

 @Test
 public void testCollision() {
  float x = 200;
  float y = 200;
  float radians = 3.1415f / 2;
  collisionDetector = new CollisionDetector();
  entity1 = mock(Entity.class);
  when(entity1.getRadius()).thenReturn(5f);
  when(entity1.getPart(PositionPart.class)).thenReturn(new PositionPart(x, y, radians));
  entity2 = mock(Entity.class);
  when(entity2.getRadius()).thenReturn(5f);
  when(entity2.getPart(PositionPart.class)).thenReturn(new PositionPart(x, y,radians ));
  assertTrue(collisionDetector.collides(entity1, entity2));
 }

 @Test
 public void testNoCollision() {
  collisionDetector = new CollisionDetector();
  entity1 = mock(Entity.class);
  when(entity1.getRadius()).thenReturn(1f);
  when(entity1.getPart(PositionPart.class)).thenReturn(new PositionPart(0, 0, 0));
  entity2 = mock(Entity.class);
  when(entity2.getRadius()).thenReturn(1f);
  when(entity2.getPart(PositionPart.class)).thenReturn(new PositionPart(2f, 0, 0));
  assertFalse(collisionDetector.collides(entity1, entity2));
 }
}