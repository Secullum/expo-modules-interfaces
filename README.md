# expo-modules-interfaces

Interfaces Java compartilhadas entre os forks Expo da Secullum (`secullum-expo-camera` e `secullum-expo-face-detector`) para o contrato de detecção facial:

- `FaceDetectionError`
- `FaceDetectionSkipped`
- `FaceDetectionUnspecifiedError`
- `FaceDetectorInterface`
- `FaceDetectorProviderInterface`
- `FacesDetectionCompleted`

# Por que este pacote existe

Essas interfaces pertenciam originalmente ao pacote `expo-modules-core`, mas foram removidas dele. A Secullum precisava delas para manter `secullum-expo-camera` e `secullum-expo-face-detector` funcionando, então passou a reinjetá-las via patch (`patches/expo-modules-core+<versão>.patch`, com `patch-package`) diretamente em `node_modules/expo-modules-core`.

Este pacote existe para eliminar esse patch: as interfaces moram aqui, versionadas normalmente, e os dois forks passam a depender deste pacote só para elas — continuam usando `expo-modules-core` normalmente para tudo o mais (`BasePackage`, `Module`, `Promise`, etc.).

# Instalação

```
npm install secullum-expo-modules-interfaces
```

ou

```
yarn add secullum-expo-modules-interfaces
```

O pacote é detectado pelo Expo autolinking (via `expo-module.config.json`) e não precisa de nenhuma configuração adicional no app consumidor.

# Estrutura

Só Android — não há equivalente iOS, pois o patch original também nunca teve.

```
android/
  build.gradle
  src/main/java/expo/modules/interfaces/facedetector/
    FaceDetectionError.java
    FaceDetectionSkipped.java
    FaceDetectionUnspecifiedError.java
    FaceDetectorInterface.java
    FaceDetectorProviderInterface.java
    FacesDetectionCompleted.java
```

# Uso em `secullum-expo-camera` / `secullum-expo-face-detector`

1. Adicionar `secullum-expo-modules-interfaces` como dependência no `package.json`.
2. No `android/build.gradle` de cada lib, adicionar:

```gradle
dependencies {
  implementation project(':expo-modules-interfaces')
  // ...demais dependências, incluindo expo-modules-core
}
```

3. Remover o patch `expo-modules-core+<versão>.patch` (ou a parte dele referente a `expo/modules/interfaces/facedetector`) dos projetos que o aplicam.

# Contribuindo

Mudanças aqui afetam diretamente `secullum-expo-camera` e `secullum-expo-face-detector`. Ao alterar qualquer interface, atualizar as duas libs e os apps que aplicam o patch antigo.
