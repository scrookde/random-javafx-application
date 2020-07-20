package io.scrook.sysinfo.system;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

public class DefaultSystemInfoProvider implements SystemInfoProvider {
  private final OperatingSystemMXBean operatingSystemMXBean;

  public DefaultSystemInfoProvider() {
    operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
  }

  @Override
  public double getFreeMemory(SizeUnit sizeMode) {
    return convertFromBytes(operatingSystemMXBean.getFreeMemorySize(), sizeMode);
  }

  @Override
  public double getUsedMemory(SizeUnit sizeMode) {
    return convertFromBytes(operatingSystemMXBean.getTotalMemorySize() - operatingSystemMXBean.getFreeMemorySize(), sizeMode);
  }

  @Override
  public double getTotalMemory(SizeUnit sizeMode){
    return convertFromBytes(operatingSystemMXBean.getTotalMemorySize(), sizeMode);
  }

  private double convertFromBytes(long bytes, SizeUnit targetUnit) {
    return switch(targetUnit) {
      case B -> bytes;
      case KB -> (double) bytes / 1024;
      case MB -> (double) bytes / 1024 / 1024;
      case GB -> (double) bytes / 1024 / 1024 / 1024;
    };
  }

}
