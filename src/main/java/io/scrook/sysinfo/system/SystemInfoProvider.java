package io.scrook.sysinfo.system;

public interface SystemInfoProvider {
  enum SizeUnit {
    B, KB, MB, GB
  }


  double getUsedMemory(SizeUnit sizeUnit);

  double getFreeMemory(SizeUnit sizeUnit);

  double getTotalMemory(SizeUnit sizeUnit);

}
