export interface UsbDetectorPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
