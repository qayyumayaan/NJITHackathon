clc
clear

[file, path] = uigetfile("/Users/qayyuma/Downloads/Rips/Grover Washington Jr/Winelight/06 Make Me A Memory copy.smores");
filename = append(path,file);
newFilename = append(erase(filename, ".smores"),".mat");
movefile(filename, newFilename);
filename = append(filename,".mat");
load(newFilename);
oldFilename = append(erase(newFilename, ".mat"),".smores");
movefile(newFilename, oldFilename);
[musicSplit] = musicDecrypter(musicEncrypted, intervalKey);
[music] = musicReassembler(musicSplit, numIntervals);

disp("Finished decrypting.");

% oldFilename = append(erase(newFilename, ".mat"),".smores");
% movefile(newFilename, oldFilename);


function [musicSplit] = musicDecrypter(musicEncrypted, intervalKey)

[a, b] = size(musicEncrypted);

musicSplit = zeros(a,b);

for i = 1:a
    for j = 1:b
        musicSplit(intervalKey(i),j) = musicEncrypted(i,j);
    end
end
end

function [music] = musicReassembler(musicSplit, numIntervals)

[a,b] = size(musicSplit);
musicLength = a*b;
music = zeros(1,musicLength);
count = 1;
for i = 1:numIntervals
    for j = 1:(floor(musicLength/numIntervals))
        bruh = musicSplit(i,j);
        music(count) = bruh;
        count = count + 1;
    end
end

end