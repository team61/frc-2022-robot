// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDStripSubsystem extends SubsystemBase {
  AddressableLED strip;
  AddressableLEDBuffer buffer;
  /** Creates a new LEDStripSubsystem. */
  public LEDStripSubsystem(int port, int length) {
    strip = new AddressableLED(port);
    buffer = new AddressableLEDBuffer(length);

    strip.setLength(buffer.getLength());
    strip.start();
  }

  public void setHSV(int index, int h, int s, int v) {
    buffer.setHSV(index, h, s, v);
  }

  public void setStripHSV(int h, int s, int v) {
    for (int i = 0; i < buffer.getLength(); i++) {
      setHSV(i, h, s, v);
    }
  }

  public void setRGB(int index, int r, int g, int b) {
    buffer.setRGB(index, r, g, b);
  }

  public void setStripRGB(int r, int g, int b) {
    for (int i = 0; i < buffer.getLength(); i++) {
      setRGB(i, r, g, b);
    }
  }

  public void setColor(int index, Color c) {
    buffer.setLED(index, c);
  }

  public void setStripColor(Color c) {
    for (int i = 0; i < buffer.getLength(); i++) {
      setColor(i, c);
    }
  }

  public void turnOff() {
    setStripRGB(0, 0, 0);
  }

  @Override
  public void periodic() {
    strip.setData(buffer);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
