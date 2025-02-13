import { registerPlugin } from '@capacitor/core';

import type { UsbDetectorPlugin } from './definitions';

const UsbDetector = registerPlugin<UsbDetectorPlugin>('UsbDetector', {
  web: () => import('./web').then((m) => new m.UsbDetectorWeb()),
});

export * from './definitions';
export { UsbDetector };
